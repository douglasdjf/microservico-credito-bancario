package com.creditobancario.microservicocartoes.domain.service;

import com.creditobancario.microservicocartoes.domain.entity.ClienteCartao;
import com.creditobancario.microservicocartoes.domain.repository.ClienteCartaoRepository;
import com.creditobancario.microservicocartoes.dto.ClienteCartaoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteCartaoService {

    @Autowired
    private ClienteCartaoRepository clienteCartaoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ClienteCartaoDTO> listarCartoesPorCPF(String cpf){
        List<ClienteCartao> clienteCartoes = clienteCartaoRepository.findByCpf(cpf);
        return clienteCartoes.stream().map(clienteCartao -> modelMapper.map(clienteCartao,ClienteCartaoDTO.class)).collect(Collectors.toList());
    }
}
