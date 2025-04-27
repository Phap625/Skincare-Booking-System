package edu.uth.jpa.controllers;
import edu.uth.jpa.models.Appointment;
import edu.uth.jpa.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import edu.uth.jpa.models.User;
import edu.uth.jpa.services.UserServices;
import java.util.List;
import java.util.Optional;

@Controller
@RestController("/admin/appointment")
public class AdminAppointmentController {
    @Autowired
    private UserServices userService;
    @Autowired
    private AppointmentRepository appointmentRepository;

//    @GetMapping
//    public ResponseEntity<List<Appointment>> getAllAppointments() {
//
//        List<Appointment> appointments = appointmentRepository.findAll();
//        return ResponseEntity.ok(appointments);
//    }

//    @GetMapping("/edit/{id}")
//    public String editAppointment(@PathVariable("id") Long id, Model model) {
//        Optional<Appointment> appointmentOpt = appointmentRepository.findById(id);
//        model.addAttribute("appointment", appointmentOpt.orElse(new Appointment()));
//        return "/master/appointment";
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteAppointment(@PathVariable("id") Long id) {
//        appointmentRepository.deleteById(id);
//        return "redirect:/admin/appointment/";
//    }
}
