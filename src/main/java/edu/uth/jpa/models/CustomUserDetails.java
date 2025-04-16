package edu.uth.jpa.models;

import edu.uth.jpa.enums.userRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import edu.uth.jpa.models.User;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public  String getUsername() {
        return user.getUsername();
    }
    public  String getEmail() {
        return user.getEmail();
    }
    public  String getPhone() {
        return user.getPhone();
    }
    public LocalDate getDob() {
        return user.getDob();
    }
    public String getRole() {
        return user.getRole();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
