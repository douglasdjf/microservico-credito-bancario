package com.creditobancario.microservicocartoes.domain.repository;

import com.creditobancario.microservicocartoes.domain.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartaoRespository extends JpaRepository<Cartao,Long> {
    List<Cartao> findByRendaLessThanEqual(BigDecimal rendaBigDecimal);
}
