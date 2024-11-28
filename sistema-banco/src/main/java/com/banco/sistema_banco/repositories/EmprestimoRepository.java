package com.banco.sistema_banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.sistema_banco.entities.Emprestimo;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByClientId(Long clientId); // Método para encontrar empréstimos por cliente
}
