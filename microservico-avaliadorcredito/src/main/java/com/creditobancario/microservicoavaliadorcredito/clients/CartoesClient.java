package com.creditobancario.microservicoavaliadorcredito.clients;

import com.creditobancario.microservicoavaliadorcredito.dto.CartaoDTO;
import com.creditobancario.microservicoavaliadorcredito.dto.ClienteCartaoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartoesClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<ClienteCartaoDTO>> getCatoesPorCpf(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
     ResponseEntity<List<CartaoDTO>> getCartoesRendaAte(@RequestParam("renda") Long renda);
}
