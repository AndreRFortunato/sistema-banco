package com.banco.sistema_banco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banco.sistema_banco.entities.PagamentoEmprestimo;

import java.util.List;

@Repository
public interface PagamentoEmprestimoRepository extends JpaRepository<PagamentoEmprestimo, Long> {
    List<PagamentoEmprestimo> findByEmprestimoId(Long emprestimoId);
}

