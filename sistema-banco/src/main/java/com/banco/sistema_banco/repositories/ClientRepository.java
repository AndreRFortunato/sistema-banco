package com.banco.sistema_banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.sistema_banco.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

    
}
