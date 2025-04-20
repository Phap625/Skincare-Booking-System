
package edu.uth.jpa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceController {

    @GetMapping("/service/massage")
    public String massage() {
        return "master/service/massage";
    }

    @GetMapping("/service/facial")
    public String facial() {
        return "master/service/facial";
    }

    @GetMapping("/service/body")
    public String body() {
        return "master/service/body";
    }

    @GetMapping("/service/hair")
    public String hair() {
        return "master/service/hair";
    }

    @GetMapping("/service/whitening")
    public String whitening() {
        return "master/service/whitening";
    }

    @GetMapping("/service/hair-removal")
    public String hairRemoval() {
        return "master/service/hair-removal";
    }
}
