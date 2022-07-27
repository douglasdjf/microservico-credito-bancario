package com.creditobancario.microservicocartoes.resource;

import com.creditobancario.microservicocartoes.domain.service.CartaoService;
import com.creditobancario.microservicocartoes.domain.service.ClienteCartaoService;
import com.creditobancario.microservicocartoes.dto.CartaoDTO;
import com.creditobancario.microservicocartoes.dto.ClienteCartaoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("cartoes")
public class CartoesResource {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status(){
        log.info("Obtendo o status do microservice de cartoes");
        return "ok";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CartaoDTO> salvar(@Valid @RequestBody CartaoDTO cartaoDTO){
        CartaoDTO cartaoSalvo = cartaoService.salvar(cartaoDTO);
        URI headerLocator = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cartaoSalvo.getId())
                .toUri();
        return ResponseEntity.created(headerLocator).body(cartaoSalvo);
    }

    @GetMapping(params = "renda")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CartaoDTO>> getCartoesRendaAte(@RequestParam("renda") Long renda){
        List<CartaoDTO> cartaoLis = cartaoService.getCartoesRendaMenorIgual(renda);
        if(CollectionUtils.isEmpty(cartaoLis))
            return  ResponseEntity.noContent().build();
       return ResponseEntity.ok(cartaoLis);
    }

    @GetMapping(params = "cpf")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ClienteCartaoDTO>> getCatoesPorCpf(@RequestParam("cpf") String cpf){
        List<ClienteCartaoDTO> clienteCartaoDTOS = clienteCartaoService.listarCartoesPorCPF(cpf);
        if(CollectionUtils.isEmpty(clienteCartaoDTOS))
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clienteCartaoDTOS);
    }
}
