package com.samsungsds.contact.repository.contact;

import com.samsungsds.contact.entity.ContactGroupMember;
import com.samsungsds.contact.entity.ContactGroupMember.ContactUserIdInProjection;

import com.samsungsds.contact.entity.ContactGroupMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContactGroupMemberRepository extends JpaRepository<ContactGroupMember, ContactGroupMemberId> {
    List<ContactGroupMember> findByGroupId(Long groupId);

    List<ContactUserIdInProjection> findAllProjectedByGroupId(long groupId);

//    void deleteAllById(List<ContactGroupMemberId> contactGroupMemberIds);
//    void saveAll(List<ContactGroupMemberId> contactGroupMemberIds);


}
