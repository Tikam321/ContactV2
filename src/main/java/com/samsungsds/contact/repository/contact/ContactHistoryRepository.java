package com.samsungsds.contact.repository.contact;

import com.samsungsds.contact.entity.ContactHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactHistoryRepository extends JpaRepository<ContactHistory, Long> {
}
