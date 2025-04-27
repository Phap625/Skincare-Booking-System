package edu.uth.jpa.controllers;

import edu.uth.jpa.models.Appointment;
import edu.uth.jpa.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // 🟢 Hiển thị form đặt dịch vụ (có thể kèm theo tên dịch vụ từ URL)
    @GetMapping
    public String showAppointmentForm(@RequestParam(value = "service", required = false) String service, Model model) {
        Appointment appointment = new Appointment();
        appointment.setService(service); // Gán tên dịch vụ nếu có
        model.addAttribute("appointment", appointment);
        return "master/appointment";
    }

    // 🟢 Lưu mới hoặc cập nhật lịch hẹn
    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute Appointment appointment) {
        appointmentRepository.save(appointment);

        return "redirect:/customer/appointment/list/" + appointment.getUsername() + "/" + appointment.getRole() ;

    }

    // 🟢 Hiển thị danh sách các lịch hẹn
    @GetMapping("/list/{username}/{role}")
    public String viewAppointments(@PathVariable("username")String username, @PathVariable("role")String role,  Model model) {
        List<Appointment> list;

        // Kiểm tra nếu là ADMIN, lấy tất cả các lịch hẹn, nếu không lấy theo username
        if (role.equals("ROLE_ADMIN")) {
            list = appointmentRepository.findAll();
        } else {
            list = appointmentRepository.findByUsername(username);
        }

        // Gán danh sách vào model và trả về view
        model.addAttribute("appointments", list);
        return "/master/appointment-list";
    }

     //🟡 Sửa lịch hẹn
    @GetMapping("/edit/{id}")
    public String editAppointment(@PathVariable("id") Long id, Model model) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
        model.addAttribute("appointment", appointmentOpt.orElse(new Appointment()));
        return "/master/appointment";
    }

    // 🔴 Xóa lịch hẹn
    @GetMapping("/delete/{id}/{username}/{role}")
    public String deleteAppointment(@PathVariable("id") Long id, @PathVariable("username")String username, @PathVariable("role")String role) {
        appointmentRepository.deleteById(id);
        return "redirect:/customer/appointment/list/" + username + "/" + role ;
    }
}
