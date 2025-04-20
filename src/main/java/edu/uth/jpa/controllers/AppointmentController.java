
package edu.uth.jpa.controllers;

import edu.uth.jpa.models.Appointment;
import edu.uth.jpa.models.User;
import edu.uth.jpa.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<String> saveAppointment(@ModelAttribute Appointment appointment, @AuthenticationPrincipal User user) {
        appointment.setUser(user);
        appointmentRepository.save(appointment);
        return ResponseEntity.ok("Đặt lịch thành công");

    }

    // 🟢 Hiển thị danh sách các lịch hẹn
    @GetMapping("/list")
    public String viewAppointments(@AuthenticationPrincipal User user, Model model) {
        List<Appointment> list = appointmentRepository.findByUserId(user.getId());
        model.addAttribute("appointments", list);
        return "master/appointment-list";
    }

    // 🟡 Sửa lịch hẹn
    @PutMapping("/edit/{id}")
    public String editAppointment(@PathVariable("id") Long id,
                                  @AuthenticationPrincipal User user,
                                  Model model) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
        if (appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            if (!appointment.getUser().getId().equals(user.getId())) {
                return "error/403"; // hoặc redirect về trang báo lỗi
            }
            model.addAttribute("appointment", appointment);
        } else {
            return "redirect:/customer/appointment/list";
        }
        return "/master/appointment-edit";
    }


    // 🔴 Xóa lịch hẹn
    @DeleteMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Long id,
                                    @AuthenticationPrincipal User user) {
        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
        if (appointmentOpt.isPresent() &&
                appointmentOpt.get().getUser().getId().equals(user.getId())) {
            appointmentRepository.deleteById(id);
        }
        return "redirect:/customer/appointment/list";
    }
}
