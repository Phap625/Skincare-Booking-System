package edu.uth.jpa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorController {

    @GetMapping("/doctor/khoa")
    public String doctorKhoa() {
        return "master/Doctor/doctor-khoa";
    }

    @GetMapping("/doctor/linh")
    public String doctorLinh() {
        return "master/Doctor/doctor-linh";
    }

    @GetMapping("/doctor/huong")
    public String doctorHuong() {
        return "master/Doctor/doctor-huong";
    }

    @GetMapping("/doctor/nghia")
    public String doctorNghia() {
        return "master/Doctor/doctor-nghia";
    }
}
