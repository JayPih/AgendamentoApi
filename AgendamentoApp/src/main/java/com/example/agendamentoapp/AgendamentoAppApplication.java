package com.example.agendamentoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgendamentoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoAppApplication.class, args);
	}

}
