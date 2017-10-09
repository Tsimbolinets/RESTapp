package com.gmail.tsimbolinetsoleg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RestappiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestappiApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(final ContactService contactService) {
		return new CommandLineRunner() {
			@Override
			public void run(String... strings) throws Exception {
				DBEntitys dbEntitys = new DBEntitys();
				contactService.addOrder(dbEntitys.addtoDB());
			}
		};
	}
}