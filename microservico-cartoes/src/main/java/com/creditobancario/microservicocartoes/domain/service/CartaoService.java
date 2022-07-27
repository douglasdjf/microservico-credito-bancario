package com.creditobancario.microservicocartoes.domain.service;

import com.creditobancario.microservicocartoes.domain.entity.Cartao;
import com.creditobancario.microservicocartoes.domain.repository.CartaoRespository;
import com.creditobancario.microservicocartoes.dto.CartaoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartaoService {

    @Autowired
    private CartaoRespository cartaoRespository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public CartaoDTO salvar(CartaoDTO cartaoDto){
        Cartao cartao = modelMapper.map(cartaoDto,Cartao.class);
        Cartao cartaoSalvo = cartaoRespository.save(cartao);
        return modelMapper.map(cartaoSalvo,CartaoDTO.class);
    }

    public List<CartaoDTO> getCartoesRendaMenorIgual(@RequestParam("renda") Long renda){
            var rendaBigDecimal = BigDecimal.valueOf(renda);
            List<Cartao> cartaoList =  cartaoRespository.findByRendaLessThanEqual(rendaBigDecimal);
            return cartaoList.stream().map(c -> modelMapper.map(c,CartaoDTO.class)).collect(Collectors.toList());
    }
}
