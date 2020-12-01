package com.hu.lingo.trainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Properties;

@SpringBootApplication
public class LingoApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(LingoApplication.class);

		Properties props = new Properties();
		props.put("spring.datasource.url", System.getenv("DB_URL"));
		props.put("spring.datasource.username", System.getenv("DB_USER"));
		props.put("spring.datasource.password", System.getenv("DB_PASS"));
		application.setDefaultProperties(props);

		application.run(args);
	}

}