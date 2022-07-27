package com.creditobancario.microservicocartoes;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableRabbit
@SpringBootApplication
public class MicroservicoCartoesApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroservicoCartoesApplication.class, args);
	}
}
