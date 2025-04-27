package edu.uth.jpa.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class  HomeController {
    @GetMapping("/")
    public String HomePage() { return "master/home_page"; } //goi den trang htmlpage
    @GetMapping("master/about")
    public String aboutPage() { return "master/about"; }
    @GetMapping("master/service")
    public String servicePage() { return "master/service"; }
    @GetMapping("master/therapist")
    public String therapistPage() {
        return "master/therapist";
    }
    @GetMapping("master/contact")
    public String contactPage() {
        return "master/contact";
    }
//    @GetMapping("master/appointment")
//    public String appointmentPage() {
//        return "master/appointment";
//    }
    @GetMapping("master/blog")
    public String blogPage() {
        return "master/blog";
    }
    @GetMapping("master/termOfService")
    public String termOfServicePage() {
        return "master/termOfService";
    }
    @GetMapping("master/policy")
    public String policyPage() {
        return "master/policy";
    }
    @GetMapping("master/quiz")
    public String quizPage() {
        return "master/quiz";
    }

    @GetMapping("/auths/login")
    public String loginPage() {
        return "master/login";
    }

    @GetMapping("/user/profile")
    public String profilePage() {
        return "master/profile";
    }

    @GetMapping("/list-user")
    public String adminPage() {
        return "master/admin/index";
    }


    @GetMapping("/list-contact")
    public String adminContactPage() {return "master/admin/contact-list"; }

    @GetMapping("/appointment-list")
    public String AppointmentPage() {
        return "master/appointment-list";
    }

}
