package com.codeOnce.technicalTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.codeOnce.technicalTest.model.entity")
public class TestTechniqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestTechniqueApplication.class, args);
	}

}
