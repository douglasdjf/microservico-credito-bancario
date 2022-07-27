package com.creditobancario.microservicoavaliadorcredito;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableRabbit
@EnableFeignClients
public class MicroservicoAvaliadorcreditoApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroservicoAvaliadorcreditoApplication.class, args);
	}
}
