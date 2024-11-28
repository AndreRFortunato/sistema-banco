package com.banco.sistema_banco.repositories;

import com.banco.sistema_banco.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

    
}
