package com.samsungsds.contact.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.samsungsds.contact.entity.ContactGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupDto {
    private final long groupID;
    private final String groupName;
    private final Integer order;
    private List<Long> userIdlist;


    public static GroupDto from(ContactGroup contactGroup, List<Long> userIds) {
        return new GroupDto(contactGroup.getGroupId(),
                contactGroup.getGroupName(),
                contactGroup.getGroupDisplayOrderNo(),
                userIds);
    }



}
