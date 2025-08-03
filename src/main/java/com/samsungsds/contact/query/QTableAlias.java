package com.samsungsds.contact.query;

import com.samsungsds.contact.entity.QContact;
import com.samsungsds.contact.entity.QContactGroup;
import com.samsungsds.contact.entity.QContactGroupMember;

public class QTableAlias {
    public static final QContact ctct = QContact.contact;
    public static final QContactGroup ctct_grp = QContactGroup.contactGroup;
    public static final QContactGroupMember ctct_grp_mem = QContactGroupMember.contactGroupMember;

}