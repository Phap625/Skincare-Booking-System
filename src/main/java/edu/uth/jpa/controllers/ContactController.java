package edu.uth.jpa.controllers;

import edu.uth.jpa.models.entity.Contact;
import edu.uth.jpa.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/master/contact")
    public String handleContactForm(@ModelAttribute Contact contact) {
        contactRepository.save(contact);
        return "master/thank-you";
    }
}
