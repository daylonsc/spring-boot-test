package com.example.testeuol.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testeuol.api.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
