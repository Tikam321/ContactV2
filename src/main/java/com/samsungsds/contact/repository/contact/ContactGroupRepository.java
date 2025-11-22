package com.samsungsds.contact.repository.contact;

import com.samsungsds.contact.dto.ContactDto;
import com.samsungsds.contact.entity.ContactGroup;
import com.samsungsds.contact.entity.ContactGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public interface ContactGroupRepository extends JpaRepository<ContactGroup, Long> {
//    List<ContactGroup> findAllByOwnerUserIdOrOrderByGroupNameAsc(long ownerUserId);
    boolean existsByOwnerUserIdAndGroupName(Long userId, String groupName);
    List<ContactGroup> findAllByOwnerUserIdOrderByGroupNameAsc(long ownerUserId);
    Optional<ContactGroup> findByOwnerUserIdAndGroupId(Long userid, Long groupId);
}
