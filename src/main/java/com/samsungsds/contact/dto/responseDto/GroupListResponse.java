package com.samsungsds.contact.dto.responseDto;

import com.samsungsds.contact.dto.GroupDto;
import lombok.Getter;

import java.util.List;
@Getter
public class GroupListResponse {
    List<GroupDto> groupList;

    public GroupListResponse(List<GroupDto> groupList) {
        this.groupList = groupList;
    }
}
