package com.creditobancario.microservicoavaliadorcredito.clients;

import com.creditobancario.microservicoavaliadorcredito.dto.DadosClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteApi {

    @GetMapping(params = "cpf")
    public ResponseEntity<DadosClienteDTO> obterPorCpf(@RequestParam("cpf") String cpf);
}
