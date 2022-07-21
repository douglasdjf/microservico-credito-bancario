package com.creditobancario.microservicoclientes.domain.service;

import com.creditobancario.microservicoclientes.domain.entity.Cliente;
import com.creditobancario.microservicoclientes.domain.repository.ClienteRepository;
import com.creditobancario.microservicoclientes.dto.ClienteDTO;
import com.creditobancario.microservicoclientes.exception.ClienteNaoEncontradoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteDTO salvar(ClienteDTO clienteDTO){
        Cliente cliente = modelMapper.map(clienteDTO,Cliente.class);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return (modelMapper.map(clienteSalvo,ClienteDTO.class));
    }

    public ClienteDTO obterPeloCpf(String cpf){
        Optional<Cliente> clienteOptional =clienteRepository.findByCpf(cpf);
        Cliente cliente =  clienteOptional.orElseThrow(()-> new ClienteNaoEncontradoException("cpf não encontrado !"));
        return modelMapper.map(cliente,ClienteDTO.class);
    }
}
