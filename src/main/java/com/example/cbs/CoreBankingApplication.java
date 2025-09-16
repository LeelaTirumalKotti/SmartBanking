package com.example.cbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.cbs"})
@EnableJpaRepositories(basePackages = "com.example.cbs.repo")
public class CoreBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreBankingApplication.class, args);
	}

}
