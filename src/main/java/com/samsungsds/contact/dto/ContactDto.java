package com.samsungsds.contact.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    private long userId;
    private String epId;
    private String localName;
    private String globalName;
    private String nickName;
    private String email;
    private String companyName;
    private String department;
    private String vip;
    private String phoneNumber;
    private String bizNumber;
    private String voipNumber;
    private String faxNumber;
    private String compCd;
    private String deptCd;
    private int userStatusCd;
    private String webConferenceCD;
    private String chargeBiz;
    private String userTypeCd;
    private String vchatCd;
    private String botProviderCd;
    private String botCommand;
    private String botGroupChat;

    //from contact
    private boolean isFavorite;

    //for top rank of botList
    private  String byName;

    private String statusMessage;
    private String profileImageUrl;

    // for executives group
    private Boolean isExecutive;
    private String  grpId;

    private Boolean isIntelNotiSender;


















}
