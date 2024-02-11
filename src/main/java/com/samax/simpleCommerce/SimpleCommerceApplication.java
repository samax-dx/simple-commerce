package com.samax.simpleCommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;


@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class SimpleCommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCommerceApplication.class, args);
	}

}
