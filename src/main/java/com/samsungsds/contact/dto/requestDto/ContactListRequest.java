package com.samsungsds.contact.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.samsungsds.contact.dto.ContactDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ContactListRequest {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ContactDto> contactDtoList;

}
