package com.creditobancario.microservicocartoes.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQueuConfig {

    @Value("${mq.queues.emissao-cartoes}")
    private String emissaoCartaoQueue;

    @Value("${mq.exchanges.emissao-cartoes-exchange}")
    private String emissaoCartaoExchange;

    @Value("${mq.bindings.emissao-cartoes-bindings}")
    private String emissaoCartaoBinding;



    @Bean
    Queue emissaoCartaoQueue(){
        return new Queue(emissaoCartaoQueue,true);
    }

    @Bean
    Exchange emissaoCartaoExchange(){
        return ExchangeBuilder.directExchange(emissaoCartaoExchange)
                .durable(true)
                .build();
    }

    @Bean
    Binding emissaoCartaoBinding(){
        return BindingBuilder
                .bind(emissaoCartaoQueue())
                .to(emissaoCartaoExchange())
                .with(emissaoCartaoBinding)
                .noargs();
    }
}
