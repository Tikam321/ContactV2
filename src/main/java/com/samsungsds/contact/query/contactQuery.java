package com.samsungsds.contact.query;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsungsds.contact.entity.Contact;
import com.samsungsds.contact.entity.QContact;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class contactQuery {
    private final JPAQueryFactory jpaQueryFactory;

    QContact contact = QTableAlias.ctct;

    public List<Contact> getcontactlist(Long contactUserId) {
        return  jpaQueryFactory.select(contact)
                .where(contact.favoriteYN.eq(1).
                        and(contact.contactUserId.eq(contactUserId))).fetch();
    }

}
