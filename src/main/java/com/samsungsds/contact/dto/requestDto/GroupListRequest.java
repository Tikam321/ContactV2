package com.samsungsds.contact.dto.requestDto;

import com.samsungsds.contact.dto.GroupDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GroupListRequest {
    private List<GroupDto> groupList;

}
