package com.marvel.avengers;

import org.springframework.boot.SpringApplication;

public class TestAvengersApplication {

	public static void main(String[] args) {
		SpringApplication.from(AvengersApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
