package com.samsungsds.contact.repository.contact;

import com.samsungsds.contact.dto.ContactDto;
import com.samsungsds.contact.entity.ContactGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactGroupRepository extends JpaRepository<ContactGroupRepository, Long> {
    List<ContactGroup> findAllByOwnerUserIdOrOrderByGroupNameAsc(long ownerUserId);
    boolean existsByOwnerUserIdAndGroupName(Long userId, String groupName);

    Optional<ContactGroup> findByOwnerUserIdAndGroupId(Long userid, Long groupId);
}
