package com.example.spring.google;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(
		nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class,
		scanBasePackages = {
				"com.example.spring.google"
		})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
