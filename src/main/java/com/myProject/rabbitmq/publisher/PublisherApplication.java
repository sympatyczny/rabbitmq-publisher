package com.myProject.rabbitmq.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class PublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublisherApplication.class, args);
	}

	@Bean
	public Docket get(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/addMessage"))
				.build()
				.apiInfo(createApiInfo());
	}

	private ApiInfo createApiInfo(){
		return new ApiInfo(
				"Publisher API",
				"RabbitMQ publisher",
				"1.00",
				"http://test.test",
				new Contact("Me", "http://test.test", "email.test@test" ),
				"my own license",
				"http://test.test/licence",
				Collections.emptyList()
		);
	}

}
