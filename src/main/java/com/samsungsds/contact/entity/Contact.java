package com.samsungsds.contact.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactUserId;

    @Column(name = "ctct_nick_nm")
    private String updateContactNickName;

    @Column(name = "fav_yn")
    private int favoriteYN;

    @Column(name = "del_yn")
    private int deleteYN;

    public Contact(Long userId, Long contactUserId) {
        this.userId = userId;
        this.contactUserId = contactUserId;
    }

    public void updateFavouriteYN(int favoriteYN) {
        this.favoriteYN = favoriteYN;
    }

    public void updateContactNickname(String nickName) {
        this.updateContactNickName = nickName;
    }

}

