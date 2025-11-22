package com.samsungsds.contact.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ctct_grp_memb")
@IdClass(ContactGroupMemberId.class)
public class ContactGroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grp_id")
    private Long groupId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ctct_usr_id")
    private Long contactUserId;

    @Column(name = "grp_nm")
    private String groupName;

    @Column(name = "grp_disp_ord_no")
    private Integer groupDisplayOrderNo;

    @Column(name = "ownr_usr_id")
    private Long ownerUserId;

    @Column(name = "del_yn")
    private int deleteYN;

    public ContactGroupMember(Long groupId, Long userId, Long contactUserId) {
        this.groupId = groupId;
        this.userId = userId;
        this.contactUserId = contactUserId;
    }

    public interface ContactUserIdInProjection {
       Long getContactUserId();
    }
}

