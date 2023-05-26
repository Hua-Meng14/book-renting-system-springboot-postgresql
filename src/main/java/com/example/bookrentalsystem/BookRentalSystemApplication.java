package com.example.bookrentalsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.bookrentalsystem.models","com.example.bookrentalsystem.controllres","com.example.bookrentalsystem.services","com.example.bookrentalsystem.repositories" })
public class BookRentalSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookRentalSystemApplication.class, args);
	}

}
