package com.creditobancario.microservicoavaliadorcredito.publisher;

import com.creditobancario.microservicoavaliadorcredito.dto.DadosSolicitacaoEmissaoCartao;
import com.netflix.discovery.converters.Auto;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SolicitacaoEmissaoCartaoPublisher {

    @Autowired
    private  RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queueEmissaoCartoes;

    public void solicitarCartao(@Payload DadosSolicitacaoEmissaoCartao dados){
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(),dados);
    }

}
