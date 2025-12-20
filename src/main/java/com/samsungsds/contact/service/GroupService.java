package com.samsungsds.contact.service;

import com.samsungsds.contact.config.properties.ContactGroupProperties;
import com.samsungsds.contact.dto.GroupDto;
import com.samsungsds.contact.dto.requestDto.GroupListRequest;
import com.samsungsds.contact.dto.requestDto.GroupRequest;
import com.samsungsds.contact.dto.responseDto.GroupListResponse;
import com.samsungsds.contact.entity.ContactGroup;
import com.samsungsds.contact.entity.ContactGroupMember;
import com.samsungsds.contact.entity.ContactGroupMemberId;
import com.samsungsds.contact.exception.exception.ContactServerException;
import com.samsungsds.contact.repository.contact.ContactGroupMemberRepository;
import com.samsungsds.contact.repository.contact.ContactGroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.samsungsds.contact.exception.ErrorType.*;
import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
@Service
public class GroupService {
    private final  ContactGroupRepository  contactGroupRepository;
    private final ContactGroupMemberRepository contactGroupMemberRepository;
    private final ContactGroupProperties contactGroupProperties;
    private final GroupCacheService groupCacheService;

//    @Cacheable(value = "groupMembersCache", key = "#groupId")
//    public List<GroupDto> getGroupMembers(Long groupId) {
//        return groupService.getGroupMembers(groupId);
//    }
//
    public List<GroupDto> getGroupList(long userId) {
        List<ContactGroup> contactGroups = contactGroupRepository
                .findAllByOwnerUserIdOrderByGroupNameAsc(userId);
        System.out.println("groupId" + contactGroups.get(0).getGroupId());
        return getGroups(contactGroups);
    }

    private List<GroupDto> getGroups(List<ContactGroup> contactGroups) {
       List<Long> groupIds = contactGroups.stream().map(ContactGroup::getGroupId).toList();
        Map<Long, List<Long>> groupMemberMap = new HashMap<>();
        for(Long groupId:groupIds) {
            groupMemberMap.put(groupId, groupCacheService.getMembersByGroupId(groupId));
        }
//                contactGroupMemberRepository.findByGroupIdIn(groupIds).stream().collect(groupingBy(ContactGroupMember::getGroupId,
//                mapping(ContactGroupMember::getContactUserId, Collectors.toList())));
        return contactGroups.stream()
                .map(group -> GroupDto.from(group, groupMemberMap.get(group.getGroupId())))
                .collect(Collectors.toList());

    }

//    @Cacheable(value = "groupMemberIcCache", key = "#groupId")
//    public List<Long> getMembersByGroupId(Long groupId) {
//        return contactGroupMemberRepository.findByGroupId(groupId).stream().map(ContactGroupMember::getContactUserId).toList();
//    }

    @Transactional
    public GroupListResponse createGroup(String groupName, Long userId) {
        checkGroupNameLength(groupName);
        checkDuplicateGroupName(groupName, userId);

        contactGroupRepository.save(new ContactGroup(groupName, userId));

        List<GroupDto> groups = getGroupList(userId);
        return new GroupListResponse(groups);
    }

    private void checkDuplicateGroupName(String groupName, Long userId) {
        boolean isGroupNameExist = contactGroupRepository
                .existsByOwnerUserIdAndGroupName(userId, groupName);

        if (isGroupNameExist) {
            throw new ContactServerException(EXISTED_GROUP, groupName);
        }
    }

    private void checkGroupNameLength(String groupName) {
        if (groupName.length() > contactGroupProperties.getNameLength()) {
            throw new ContactServerException(EXCEEDED_GROUP_LENGTH_LIMIT);
        }
    }

    @Transactional
    public void updateGroupName(Long userId, long groupId, String groupName) {
        checkGroupNameLength(groupName);
        ContactGroup contactGroup = contactGroupRepository.findByOwnerUserIdAndGroupId(userId, groupId)
                .orElseThrow(() -> new ContactServerException(NOT_EXISTED_GROUP));
        checkDuplicateGroupName(groupName, userId);
        contactGroup.setGroupName(groupName);
    }

    @Transactional
    public void updateGroupMember(GroupRequest group,Long userId) {
        List<Long> reqGroupMemberIds = group.getUserIdList();
        Set<Long> newGroupMembers = new HashSet<Long>(reqGroupMemberIds);
        Long reqGroupId = group.getGroupID();

//        checkGroupMemberLimit(reqGroupMemberIds.size());

        Set<Long> groupMembers = getGroupMembers(group.getGroupID());
        Set<Long> membersToAdd = newGroupMembers.stream()
                .filter(memberId -> !groupMembers.contains(memberId)).collect(Collectors.toSet());

        Set<Long> memebrsToDelete = groupMembers.stream()
                .filter(memberId -> !newGroupMembers.contains(memberId)).collect(Collectors.toSet());

        deleteContactGroupMember(reqGroupId, memebrsToDelete, userId);
        addContactGroupMember(reqGroupId, membersToAdd,userId);

    }

    private void addContactGroupMember(Long reqGroupId, Set<Long> membersToAdd, Long userId) {
        groupCacheService.addGroupMember(reqGroupId,membersToAdd,userId);
//        List<ContactGroupMember> groupMemberIds = membersToAdd.stream()
//                .map(memberId -> new ContactGroupMember(reqGroupId,userId, memberId)).toList();
//
//        contactGroupMemberRepository.saveAll(groupMemberIds);
    }

    private void deleteContactGroupMember(Long reqGroupId, Set<Long> memebrsToDelete, Long userId) {
        List<ContactGroupMemberId> groupMemberIds = memebrsToDelete.stream()
                .map(memberId -> new ContactGroupMemberId(reqGroupId,userId, memberId)).toList();
        contactGroupMemberRepository.deleteAllById(groupMemberIds);

    }

    public Set<Long> getGroupMembers(Long groupId) {
        return contactGroupMemberRepository.findAllProjectedByGroupId(groupId).stream()
                .map(ContactGroupMember.ContactUserIdInProjection::getContactUserId).collect(Collectors.toSet());
    }

    private void checkGroupMemberLimit(int groupMemberSize) {
        if (groupMemberSize > contactGroupProperties.getMaxCount()) {
            throw new ContactServerException(EXCEEDED_GROUP_LENGTH_LIMIT);
        }
    }

    @Transactional
    public void deleteGroup(GroupListRequest groupListRequest, Long userId){
        List<ContactGroup> contactGroups = getContactGroups(userId);
        Set<Long> contactGroupIds = contactGroups.stream()
                .map(ContactGroup::getGroupId).collect(Collectors.toSet());

        for(GroupDto groupDto: groupListRequest.getGroupList()) {

            Long groupId = groupDto.getGroupID();
            Set<Long> groupMembers = getGroupMembers(groupId);
            deleteContactGroupMember(groupId, groupMembers,userId);
            deleteContactGroup(groupId);
        }
    }

    private void deleteContactGroup(Long groupId) {
        contactGroupRepository.deleteById(groupId);
    }

    private List<ContactGroup> getContactGroups(Long userId) {
        List<ContactGroup> contactGroups = contactGroupRepository
                .findAllByOwnerUserIdOrderByGroupNameAsc(userId);
        return contactGroups;
    }


}
