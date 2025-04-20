package edu.uth.jpa.repositories;

import edu.uth.jpa.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}