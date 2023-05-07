package com.codeOnce.technicalTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Technical test APIs", version = "1.0", description = "Product Operations"))

@EntityScan(basePackages = "com.codeOnce.technicalTest.model.entity")
public class TestTechniqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestTechniqueApplication.class, args);
	}

}
