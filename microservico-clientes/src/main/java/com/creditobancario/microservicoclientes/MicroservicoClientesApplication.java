package com.creditobancario.microservicoclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroservicoClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicoClientesApplication.class, args);
	}

}
