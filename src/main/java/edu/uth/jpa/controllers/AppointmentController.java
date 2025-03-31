package edu.uth.jpa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    // Hiển thị trang đặt dịch vụ
    @GetMapping
    public String showAppointmentForm() {
        return "master/appointment";  // đúng path của file appointment.html
    }

    // Nhận dữ liệu từ form và xử lý
    @PostMapping("/save")
    public String saveAppointment(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String date,
            @RequestParam String department,
            @RequestParam String doctor,
            @RequestParam(required = false) String message,
            Model model
    ) {
        // In ra console để kiểm tra dữ liệu
        System.out.println("📝 ĐẶT DỊCH VỤ MỚI:");
        System.out.println("👤 Họ tên: " + name);
        System.out.println("📧 Email: " + email);
        System.out.println("📱 SĐT: " + phone);
        System.out.println("📅 Thời gian: " + date);
        System.out.println("💆 Dịch vụ: " + department);
        System.out.println("👨‍⚕️ Bác sĩ: " + doctor);
        System.out.println("🗒 Ghi chú: " + message);

        // Gửi thông báo về cho view
        model.addAttribute("successMessage", "Cảm ơn bạn đã đặt dịch vụ! Chúng tôi sẽ liên hệ lại sớm.");

        // Quay lại trang appointment (hoặc điều hướng tới trang cảm ơn nếu muốn)
        return "master/appointment";
    }
}
