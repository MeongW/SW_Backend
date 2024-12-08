package com.aisinna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class AisinnaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AisinnaApplication.class, args);
	}

}
