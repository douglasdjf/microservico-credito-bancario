package com.creditobancario.microservicocartoes.listerners;

import com.creditobancario.microservicocartoes.domain.entity.Cartao;
import com.creditobancario.microservicocartoes.domain.entity.ClienteCartao;
import com.creditobancario.microservicocartoes.domain.repository.CartaoRespository;
import com.creditobancario.microservicocartoes.domain.repository.ClienteCartaoRepository;
import com.creditobancario.microservicocartoes.dto.DadosSolicitacaoEmissaoCartaoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmissoesCartoesListerner {

    @Autowired
    private CartaoRespository cartaoRespository;

    @Autowired
    private ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receberSolicitacaoEmissao(@Payload DadosSolicitacaoEmissaoCartaoDTO payload){
        log.info(payload.toString());
        try{
            Cartao cartao = cartaoRespository.findById(payload.getIdCartao()).orElseThrow();
            ClienteCartao clienteCartao = ClienteCartao.builder()
                                                .cartao(cartao)
                                                 .cpf(payload.getCpf())
                                                .limite(payload.getLimiteLiberado())
                                                .build();

            clienteCartaoRepository.save(clienteCartao);

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
