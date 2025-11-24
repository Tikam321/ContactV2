package com.samsungsds.contact.service;

import com.samsungsds.contact.config.properties.ContactGroupProperties;
import com.samsungsds.contact.dto.GroupDto;
import com.samsungsds.contact.entity.ContactGroup;
import com.samsungsds.contact.repository.contact.ContactGroupMemberRepository;
import com.samsungsds.contact.repository.contact.ContactGroupRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @Mock
    private  ContactGroupRepository contactGroupRepository;
    @Mock
    private  ContactGroupMemberRepository contactGroupMemberRepository;
    @Mock
    private  ContactGroupProperties contactGroupProperties;

    @Mock
    private  GroupCacheService groupCacheService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @InjectMocks
    private GroupService groupService; // Class under test

//    @Test
//    void testGetUserNameById() {
//        User user = new User(1L, "Tikam");
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//
//        String result = userService.getUserNameById(1L);
//
//        assertEquals("Tikam", result);
//        verify(userRepository, times(1)).findById(1L);
//    }
    @Test
    void getGroupList() {
        //given
        ContactGroup contactGroup = Mockito.mock(ContactGroup.class);
        Mockito.when(contactGroupRepository.findAllByOwnerUserIdOrderByGroupNameAsc(Mockito.eq(12L)))
                .thenReturn(Collections.singletonList(contactGroup));

        //when
        List<GroupDto> ctGroup = groupService.getGroupList(12L);

    }

    @Test
    void getMembersByGroupId() {
    }

    @Test
    void createGroup() {
    }

    @Test
    void updateGroupName() {
    }

    @Test
    void updateGroupMember() {
    }

    @Test
    void getGroupMembers() {
    }

    @Test
    void deleteGroup() {
    }
}