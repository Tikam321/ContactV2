package com.samsungsds.contact.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactGroupMemberId implements Serializable {

    @Column(name = "grp_id")
    private Long groupId;

    @Column(name = "usr_id")
    private Long userId;

    @Column(name = "ctct_usr_id")
    private Long contactUserId;
}

