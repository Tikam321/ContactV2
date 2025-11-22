package com.samsungsds.contact.controller;

import com.samsungsds.contact.dto.GroupDto;
import com.samsungsds.contact.dto.requestDto.ContactListRequest;
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
@RequestMapping("/contact")
//@RequiredArgsConstructor
public class ContactController {
    private final GroupService groupService;
    private final ContactService contactService;

    public ContactController(GroupService groupService, ContactService contactService) {
        this.groupService = groupService;
        this.contactService = contactService;
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<GroupDto>> getContactList(@PathVariable Long userId) {
        return ResponseEntity.ok(contactService.getContactList(userId));
    }

    @GetMapping("/block/{userId}")
    public ResponseEntity<List<GroupDto>> getBlockedContact(@PathVariable Long userId) {
        return ResponseEntity.ok(contactService.getContactList(userId));
    }

    @PostMapping("/block/{userId}")
    public ResponseEntity<List<GroupDto>> addContact(@PathVariable Long userId, @RequestParam Long groupId) {
        return ResponseEntity.ok(contactService.getContactList(userId));
    }

    @PostMapping("user/move")
    public ResponseEntity<Object> secureMoveContact(@RequestParam Long fromGroupId,@RequestParam Long toGroupId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("user/delete")
    public ResponseEntity<Object> deleteContact(@RequestBody ContactListRequest contactListRequest, @RequestParam Long groupId) {
        return ResponseEntity.ok().build();
    }

}
