package com.samsungsds.contact.repository.contact;

import com.samsungsds.contact.entity.Contact;
import com.samsungsds.contact.entity.ContactId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, ContactId> {

}
