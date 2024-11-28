package com.banco.sistema_banco.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banco.sistema_banco.entities.Parcelas;

public interface ParcelasRepository extends JpaRepository<Parcelas, Long> {
    List<Parcelas> findByEmprestimoId(Long emprestimoId);
    List<Parcelas> findByPagamentoEmprestimoId(Long pagamentoEmprestimoId);
}