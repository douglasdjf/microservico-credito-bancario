package com.creditobancario.microservicoavaliadorcredito.service;

import com.creditobancario.microservicoavaliadorcredito.clients.CartoesClient;
import com.creditobancario.microservicoavaliadorcredito.clients.ClienteApi;
import com.creditobancario.microservicoavaliadorcredito.dto.*;
import com.creditobancario.microservicoavaliadorcredito.exception.DadosClienteNotFoundException;
import com.creditobancario.microservicoavaliadorcredito.exception.ErroComunicacaoMicroservicoException;
import com.creditobancario.microservicoavaliadorcredito.exception.ErrorSolicitacaoCartaoException;
import com.creditobancario.microservicoavaliadorcredito.publisher.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliadorCreditoService {

    @Autowired
    private ClienteApi clienteApi;

    @Autowired
    private CartoesClient cartoesClient;

    @Autowired
    private SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;

    public SituacaoClienteDTO obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroservicoException {
        try {
            DadosClienteDTO dadosClienteDTO = clienteApi.obterPorCpf(cpf).getBody();
            List<ClienteCartaoDTO> cartoesClientDTO = cartoesClient.getCatoesPorCpf(cpf).getBody();

            return SituacaoClienteDTO
                    .builder()
                    .cliente(dadosClienteDTO)
                    .cartoes(cartoesClientDTO)
                    .build();
        } catch (FeignException.FeignClientException ex){
          if(HttpStatus.NOT_FOUND.value() == ex.status()){
                throw new DadosClienteNotFoundException();
          }
          throw  new ErroComunicacaoMicroservicoException(ex.getMessage(),ex.status());
        }
    }

    public RetornoAvaliacaoClienteDTO realizarAvaliacao(String cpf, Long renda)  throws DadosClienteNotFoundException, ErroComunicacaoMicroservicoException {
        try {

            DadosClienteDTO dadosClienteDTO = clienteApi.obterPorCpf(cpf).getBody();
            List<CartaoDTO> cartoes = cartoesClient.getCartoesRendaAte(renda).getBody();

            var listaCartoesAprovados = cartoes.stream().map(cartao -> {


                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosClienteDTO.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovadoDTO aprovado = CartaoAprovadoDTO
                        .builder()
                        .cartao(cartao.getNome())
                        .bandeira(cartao.getBandeira())
                        .limiteAprovado(limiteAprovado)
                        .build();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoClienteDTO(listaCartoesAprovados);

        } catch (FeignException.FeignClientException ex){
            if(HttpStatus.NOT_FOUND.value() == ex.status()){
                throw new DadosClienteNotFoundException();
            }
            throw  new ErroComunicacaoMicroservicoException(ex.getMessage(),ex.status());
        }
    }

    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao){

        try{
            emissaoCartaoPublisher.solicitarCartao(dadosSolicitacaoEmissaoCartao);
            var protocolo = UUID.randomUUID().toString();
            return ProtocoloSolicitacaoCartao.builder().protocolo(protocolo).build();
        }catch (Exception ex){
                throw new ErrorSolicitacaoCartaoException(ex.getMessage());
        }
    }
}
