package com.samsungsds.contact.service;

import com.samsungsds.contact.dto.GroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final GroupService groupService;


    public List<GroupDto> getContactList(Long userId) {
        List<GroupDto> groupDtoList = groupService.getGroupList(userId);
        return groupDtoList;
    }



}
