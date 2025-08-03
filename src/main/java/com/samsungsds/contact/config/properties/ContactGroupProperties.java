package com.samsungsds.contact.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties("contact-group")
public class ContactGroupProperties {
    private final Integer maxCount;
    private final Integer nameLength;

    @ConstructorBinding
    public ContactGroupProperties(Integer maxCount, Integer nameLength) {
        this.maxCount = maxCount;
        this.nameLength = nameLength;
    }
}
