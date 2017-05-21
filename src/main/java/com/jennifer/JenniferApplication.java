package com.jennifer;

import com.jennifer.service.StorageService;
import com.jennifer.service.properties.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class JenniferApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JenniferApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JenniferApplication.class);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (String... args) -> {
			//storageService.deleteAll();
			storageService.init();
		};
	}
}
