package edu.uth.jpa.controllers;

import edu.uth.jpa.models.Contact;
import edu.uth.jpa.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping("/contact")
    public ResponseEntity<?> handleContactForm(@RequestBody Map<String, String> contactData) {
        // Xử lý dữ liệu form (lưu vào database, gửi email, v.v.)
        String name = contactData.get("name");
        String email = contactData.get("email");
        String subject = contactData.get("subject");
        String message = contactData.get("message");
        Contact contact = new Contact(name, email, subject, message);
        contactRepository.save(contact);
        // Trả về phản hồi thành công
        return ResponseEntity.ok(Map.of("message", "Contact form submitted successfully"));
    }
}
