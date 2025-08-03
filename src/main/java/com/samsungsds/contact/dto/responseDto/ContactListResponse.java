package com.samsungsds.contact.dto.responseDto;

import com.samsungsds.contact.dto.ContactDto;
import com.samsungsds.contact.dto.GroupDto;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ContactListResponse {
    private long syncTime;
    private List<GroupDto> groupList;
    private List<ContactDto> contactList;
    public boolean hasNoData() {
        return CollectionUtils.isEmpty(groupList) && CollectionUtils.isEmpty(contactList);
    }

}
