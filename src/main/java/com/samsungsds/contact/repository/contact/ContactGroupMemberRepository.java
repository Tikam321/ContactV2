package com.samsungsds.contact.repository.contact;

import com.samsungsds.contact.entity.ContactGroupMember;
import com.samsungsds.contact.entity.ContactGroupMember.ContactUserIdInProjection;

import com.samsungsds.contact.entity.ContactGroupMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactGroupMemberRepository extends JpaRepository<ContactGroupMember, ContactGroupMemberId> {
    List<ContactGroupMember> findByGroupIdIn(List<Long> groupIds);

    List<ContactUserIdInProjection> findByGroupIdIn(long groupId);

//    void deleteAllById(List<ContactGroupMemberId> contactGroupMemberIds);
//    void saveAll(List<ContactGroupMemberId> contactGroupMemberIds);


}
