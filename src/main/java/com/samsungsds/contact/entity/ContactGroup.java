package com.samsungsds.contact.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ctct_grp")
public class ContactGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grp_id")
    private Long groupId;

    @Column(name = "grp_nm")
    private String groupName;

    @Column(name = "grp_disp_ord_no")
    private Integer groupDisplayOrderNo;

    @Column(name = "ownr_usr_id")
    private Long ownerUserId;

    @Column(name = "del_yn")
    private int deleteYN;

    @Column(name = "grp_img_url")
    private String groupImageUrl;

    @Column(name = "grp_img_upd_utmx")
    private Long groupImageUpdatedUnixTime;

    public void updateGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void updateGroupOrder(Integer orderNumber) {
        this.groupDisplayOrderNo = orderNumber;
    }

    public ContactGroup(String groupName, Long ownerUserId) {
        this.groupName = groupName;
        this.ownerUserId = ownerUserId;
    }
}

