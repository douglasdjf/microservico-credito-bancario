package com.creditobancario.microservicoavaliadorcredito.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQueuConfig {

    @Value("${mq.queues.emissao-cartoes}")
    private String emissaoCartaoQueue;

    @Bean
    Queue emissaoCartaoQueue(){
        return new Queue(emissaoCartaoQueue,true);
    }

}
