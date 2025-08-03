package com.samsungsds.contact.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ContactId {

    @Column(name = "ctct_usr_id")
    private Long contactUserId;

    @Column(name = "usr_id")
    private Long userId;

}


