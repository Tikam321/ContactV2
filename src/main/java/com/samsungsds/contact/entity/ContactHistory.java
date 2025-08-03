package com.samsungsds.contact.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ctct_hist")
public class ContactHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ctct_hist_id")
    private Integer contactHistoryId;

    @Column(name = "usr_id")
    private Long userId;

    @Column(name = "ctct_usr_id")
    private Long contactUserId;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "act_typ_cd")
    private Integer activeTypeCode;

    @Column(name = "act_val")
    private String activeValue;

    public ContactHistory(Long userId, Long contactUserId, Long groupId, Integer activeTypeCode, String activeValue) {
        this.userId = userId;
        this.contactUserId = contactUserId;
        this.groupId = groupId;
        this.activeTypeCode = activeTypeCode;
        this.activeValue = activeValue;
    }
}

