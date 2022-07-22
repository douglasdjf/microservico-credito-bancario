package com.creditobancario.microservicocartoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroservicoCartoesApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroservicoCartoesApplication.class, args);
	}
}
