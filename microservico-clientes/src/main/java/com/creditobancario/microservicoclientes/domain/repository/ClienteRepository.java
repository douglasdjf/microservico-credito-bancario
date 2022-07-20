package com.creditobancario.microservicoclientes.domain.repository;

import com.creditobancario.microservicoclientes.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente,Long> {
}
