package edu.uth.jpa.filters;

import edu.uth.jpa.jwt.JwtUtil;
import edu.uth.jpa.services.UserServices;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil; // Đảm bảo rằng JwtUtil được khai báo và cấu hình đúng
    private final UserServices userServices;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, @Lazy UserServices userServices) {
        this.jwtUtil = jwtUtil;
        this.userServices = userServices;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7).trim(); // Lấy token từ header
            String username = jwtUtil.extractUsername(token); // Lấy username từ token

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Nếu chưa có authentication, tìm UserDetails
                UserDetails userDetails = userServices.loadUserByUsername(username);

                if (jwtUtil.validateToken(token, userDetails)) {
                    // Nếu token hợp lệ, tạo Authentication token và set vào SecurityContext
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response); // Tiếp tục với filter chain
    }
}
