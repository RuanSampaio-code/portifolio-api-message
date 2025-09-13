package br.com.apimessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class ApiMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMessageApplication.class, args);
	}

}
