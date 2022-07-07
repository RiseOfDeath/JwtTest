package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Date date = new Date();

		System.out.println(TimeZone.getDefault());
		System.out.println(date);

		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		System.out.println(TimeZone.getDefault());
		System.out.println(date);

		SpringApplication.run(DemoApplication.class, args);
	}

}
