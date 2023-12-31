package com.lucalucenak.Noxus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NoxusApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoxusApplication.class, args);
	}

}
