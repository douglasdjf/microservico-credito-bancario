package com.creditobancario.microservicoclientes.resource;

import com.creditobancario.microservicoclientes.domain.service.ClienteService;
import com.creditobancario.microservicoclientes.dto.ClienteDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("clientes")
public class ClientesResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String status(){
        log.info("Obtendo o status do microservice de clientes");
        return "ok";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteDTO> salvar(@Valid  @RequestBody ClienteDTO clienteDTO){
        var cliente = clienteService.salvar(clienteDTO);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
       return ResponseEntity.created(headerLocation).body(cliente);
    }

    @GetMapping(params = "cpf")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClienteDTO> obterPorCpf(@RequestParam("cpf") String cpf){
        var cliente = clienteService.obterPeloCpf(cpf);
        return ResponseEntity.ok(cliente);
    }
}
