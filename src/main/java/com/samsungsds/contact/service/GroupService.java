package com.samsungsds.contact.service;

import com.samsungsds.contact.config.properties.ContactGroupProperties;
import com.samsungsds.contact.dto.GroupDto;
import com.samsungsds.contact.dto.requestDto.GroupListRequest;
import com.samsungsds.contact.dto.requestDto.GroupRequest;
import com.samsungsds.contact.dto.responseDto.GroupListResponse;
import com.samsungsds.contact.entity.ContactGroup;
import com.samsungsds.contact.entity.ContactGroupMember;
import com.samsungsds.contact.entity.ContactGroupMemberId;
import com.samsungsds.contact.exception.exception.ContactServiceException;
import com.samsungsds.contact.exception.exception.ErrorType;
import com.samsungsds.contact.repository.contact.ContactGroupMemberRepository;
import com.samsungsds.contact.repository.contact.ContactGroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.samsungsds.contact.exception.exception.ErrorType.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;

@RequiredArgsConstructor
@Service
public class GroupService {
    private final  ContactGroupRepository  contactGroupRepository;
    private final ContactGroupMemberRepository contactGroupMemberRepository;
    private final ContactGroupProperties contactGroupProperties;

    public List<GroupDto> getGroupList(long userId) {
        List<ContactGroup> contactGroups = contactGroupRepository
                .findAllByOwnerUserIdOrOrderByGroupNameAsc(userId);
        return getGroups(contactGroups);
    }

    private List<GroupDto> getGroups(List<ContactGroup> contactGroups) {
       List<Long> groupIds = contactGroups.stream().map(ContactGroup::getGroupId).toList();
        Map<Long, List<Long>> groupMemberMap = contactGroupMemberRepository.findByGroupIdIn(groupIds).stream().collect(groupingBy(ContactGroupMember::getGroupId,
                mapping(ContactGroupMember::getContactUserId, Collectors.toList())));

        return contactGroups.stream()
                .map(group -> GroupDto.from(group, groupMemberMap.get(group.getGroupId())))
                .collect(Collectors.toList());

    }

    public GroupListResponse createGroup(String groupName, Long userId) {
        checkGroupNameLength(groupName);
        checkDuplicateGroupName(groupName, userId);

        List<GroupDto> groups = getGroupList(userId);
        return new GroupListResponse(groups);

    }

    private void checkDuplicateGroupName(String groupName, Long userId) {
        boolean isGroupNameExist = contactGroupRepository
                .existsByOwnerUserIdAndGroupName(userId, groupName);

        if (isGroupNameExist) {
            throw new ContactServiceException(EXISTED_GROUP, groupName);
        }
    }

    private void checkGroupNameLength(String groupName) {
        if (groupName.length() > contactGroupProperties.getNameLength()) {
            throw new ContactServiceException(EXCEEDED_GROUP_LENGTH_LIMIT);
        }
    }

    public void updateGroupName(Long userId, long groupId, String groupName) {
        checkGroupNameLength(groupName);
        ContactGroup contactGroup = contactGroupRepository.findByOwnerUserIdAndGroupId(userId, groupId)
                .orElseThrow(() -> new ContactServiceException(NOT_EXISTED_GROUP));
        checkDuplicateGroupName(groupName, userId);
        contactGroup.updateGroupName(groupName);
    }

    @Transactional
    public void updateGroupMember(GroupRequest group,Long userId) {
        List<Long> reqGroupMemberIds = group.getUserIdList();
        Set<Long> newGroupMembers = new HashSet<Long>(reqGroupMemberIds);
        Long reqGroupId = group.getGroupID();

        checkGroupMemberLimit(reqGroupMemberIds.size());

        Set<Long> groupMembers = getGroupMembers(group.getGroupID());
        Set<Long> membersToAdd = newGroupMembers.stream()
                .filter(memberId -> !groupMembers.contains(memberId)).collect(Collectors.toSet());

        Set<Long> memebrsToDelete = groupMembers.stream()
                .filter(memberId -> !newGroupMembers.contains(memberId)).collect(Collectors.toSet());

        deleteContactGroupMember(reqGroupId, memebrsToDelete, userId);
        addContactGroupMember(reqGroupId, membersToAdd,userId);

    }

    private void addContactGroupMember(Long reqGroupId, Set<Long> membersToAdd, Long userId) {
        List<ContactGroupMember> groupMemberIds = membersToAdd.stream()
                .map(memberId -> new ContactGroupMember(reqGroupId,userId, memberId)).toList();
        contactGroupMemberRepository.saveAll(groupMemberIds);
    }

    private void deleteContactGroupMember(Long reqGroupId, Set<Long> memebrsToDelete, Long userId) {
        List<ContactGroupMemberId> groupMemberIds = memebrsToDelete.stream()
                .map(memberId -> new ContactGroupMemberId(reqGroupId,userId, memberId)).toList();
        contactGroupMemberRepository.deleteAllById(groupMemberIds);

    }

    public Set<Long> getGroupMembers(Long groupId) {
        return contactGroupMemberRepository.findByGroupIdIn(groupId).stream()
                .map(ContactGroupMember.ContactUserIdInProjection::contactUserId).collect(Collectors.toSet());
    }

    private void checkGroupMemberLimit(int groupMemberSize) {
        if (groupMemberSize > contactGroupProperties.getMaxCount()) {
            throw new ContactServiceException(EXCEEDED_GROUP_LENGTH_LIMIT);
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
                .findAllByOwnerUserIdOrOrderByGroupNameAsc(userId);
        return contactGroups;
    }


}
