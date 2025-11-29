package com.samsungsds.contact.controller;

import com.samsungsds.contact.dto.GroupDto;
import com.samsungsds.contact.dto.requestDto.GroupListRequest;
import com.samsungsds.contact.dto.requestDto.GroupRequest;
import com.samsungsds.contact.dto.responseDto.GroupListResponse;
import com.samsungsds.contact.service.ContactService;
import com.samsungsds.contact.service.GroupService;
import jakarta.websocket.server.PathParam;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final ContactService contactService;

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<GroupDto>> getContactList(@PathVariable Long userId) {
        return ResponseEntity.ok(groupService.getGroupList(userId));
    }

    @PostMapping("/{userId}")
    public GroupListResponse createGroup(@PathVariable Long userId, @RequestParam String groupName) {
        return groupService.createGroup(groupName, userId);
    }

    @GetMapping("/modify/name/{userId}")
    public ResponseEntity<Objects> updateGroupName(@PathVariable Long userId, @RequestParam Long  groupId, @RequestParam String groupName) {
        groupService.updateGroupName(userId, groupId, groupName);
        return  ResponseEntity.ok().build();
    }

    @PostMapping("/modify/member/{userId}")
    public ResponseEntity<Objects> updateGroupMember(@PathVariable Long userId, @RequestBody @NonNull GroupRequest groupRequest) {
        groupService.updateGroupMember(groupRequest, userId );
        return  ResponseEntity.ok().build();
    }

    @PostMapping("/modify/group/delete/{userId}")
    public ResponseEntity<Objects> deleteGroup(@PathVariable Long userId, @RequestBody @NonNull GroupListRequest groupListRequest) {
        groupService.deleteGroup(groupListRequest, userId);
        return  ResponseEntity.ok().build();
    }



}
