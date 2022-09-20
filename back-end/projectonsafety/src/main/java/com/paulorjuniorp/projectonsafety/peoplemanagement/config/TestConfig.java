package com.paulorjuniorp.projectonsafety.peoplemanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.paulorjuniorp.projectonsafety.peoplemanagement.services.DBService;


@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public void instantiateDB() {
		this.dbService.instantiateDB();
	}
}
