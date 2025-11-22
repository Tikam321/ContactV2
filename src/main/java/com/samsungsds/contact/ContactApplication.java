package com.samsungsds.contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableCaching
public class ContactApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactApplication.class, args);
	}
}
