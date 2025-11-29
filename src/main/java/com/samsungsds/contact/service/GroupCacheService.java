package com.samsungsds.contact.service;

import com.samsungsds.contact.entity.ContactGroupMember;
import com.samsungsds.contact.repository.contact.ContactGroupMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GroupCacheService {
    private final ContactGroupMemberRepository contactGroupMemberRepository;

    @Cacheable(value = "groupMemberCache", key = "#groupId")
    public List<Long> getMembersByGroupId(Long groupId) {
        System.out.println("DB hit ");
        return contactGroupMemberRepository.findByGroupId(groupId).stream().map(ContactGroupMember::getContactUserId).toList();
    }

    @CacheEvict(value = "groupMemberCache", key = "#groupId")
    public void addGroupMember(Long groupId, Set<Long> memberIds, Long userId) {
        List<ContactGroupMember> groupMemberIds = memberIds.stream()
                .map(memberId -> new ContactGroupMember(groupId,userId, memberId)).toList();
        contactGroupMemberRepository.saveAll(groupMemberIds);
        System.out.println("the group db cache is refreshed after new addition of members");
    }

}
