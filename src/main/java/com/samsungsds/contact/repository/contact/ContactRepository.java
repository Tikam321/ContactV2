package com.samsungsds.contact.repository.contact;

import com.samsungsds.contact.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
