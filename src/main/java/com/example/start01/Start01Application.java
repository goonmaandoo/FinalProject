package com.example.start01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.start01.dao")
public class Start01Application {

	public static void main(String[] args) {
		SpringApplication.run(Start01Application.class, args);
	}

}
