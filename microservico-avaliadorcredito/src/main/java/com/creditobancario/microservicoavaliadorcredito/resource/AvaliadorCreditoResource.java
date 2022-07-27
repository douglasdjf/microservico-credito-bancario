package com.creditobancario.microservicoavaliadorcredito.resource;

import com.creditobancario.microservicoavaliadorcredito.dto.DadosAvaliacaoDTO;
import com.creditobancario.microservicoavaliadorcredito.dto.SituacaoClienteDTO;
import com.creditobancario.microservicoavaliadorcredito.exception.DadosClienteNotFoundException;
import com.creditobancario.microservicoavaliadorcredito.exception.ErroComunicacaoMicroservicoException;
import com.creditobancario.microservicoavaliadorcredito.service.AvaliadorCreditoService;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoResource {

    @Autowired
    private AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping
    public String status(){
        log.info("Obtendo o status do microservice de avaliador credito");
        return "ok";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity<?> consultaSituacaoCliente(@RequestParam("cpf") String cpf){
        try {
            SituacaoClienteDTO situacaoClienteDTO = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoClienteDTO);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicoException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacaoDTO dados){
        try {
               return ResponseEntity.ok(avaliadorCreditoService.realizarAvaliacao(dados.getCpf(), dados.getRenda()));
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicoException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
