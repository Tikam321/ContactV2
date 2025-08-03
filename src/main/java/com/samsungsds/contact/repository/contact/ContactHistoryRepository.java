package com.samsungsds.contact.repository.contact;

import com.samsungsds.contact.entity.ContactHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactHistoryRepository extends JpaRepository<ContactHistory, Long> {
}
