package edu.uth.jpa.controllers;

import edu.uth.jpa.models.entity.Appointment;
import edu.uth.jpa.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/appointment") // ✅ Đặt prefix rõ ràng và nhất quán
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // 🟢 Hiển thị form đặt dịch vụ (mới hoặc chỉnh sửa)
    @GetMapping
    public String showAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "master/appointment";
    }

    // 🟢 Lưu mới hoặc cập nhật lịch hẹn
    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute Appointment appointment) {
        appointmentRepository.save(appointment);
        return "redirect:/master/appointment/list"; // chuyển về danh sách sau khi lưu
    }

    // 🟢 Hiển thị danh sách các lịch hẹn
    @GetMapping("/list")
    public String viewAppointments(Model model) {
        List<Appointment> list = appointmentRepository.findAll();
        model.addAttribute("appointments", list);
        return "master/appointment-list";
    }

    // 🟡 Sửa lịch hẹn
    @GetMapping("/edit/{id}")
    public String editAppointment(@PathVariable("id") Long id, Model model) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
        model.addAttribute("appointment", appointmentOpt.orElse(new Appointment()));
        return "master/appointment";
    }

    // 🔴 Xóa lịch hẹn
    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Long id) {
        appointmentRepository.deleteById(id);
        return "redirect:/master/appointment/list";
    }
}
