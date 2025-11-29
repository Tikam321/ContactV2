package com.samsungsds.contact.dto.requestDto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupRequest {
    private long groupID;
    private String groupName;
    private Integer order;
    private String imageUrl;
    private List<Long> userIdList;
}
