package com.banco.sistema_banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.sistema_banco.entities.Parcelas;
import com.banco.sistema_banco.repositories.ParcelasRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParcelasService {

    @Autowired
    private ParcelasRepository parcelasRepository;

    public List<Parcelas> findAll(){
        return parcelasRepository.findAll();
    }

    public Parcelas saveParcela(Parcelas parcelas) {
        parcelas.calcularTaxaDeJuros();
        return parcelasRepository.save(parcelas);
    }

    public List<Parcelas> getParcelasByEmprestimoId(Long emprestimoId) {
        return parcelasRepository.findByEmprestimoId(emprestimoId);
    }

    public List<Parcelas> getParcelasByPagamentoEmprestimoId(Long pagamentoEmprestimoId) {
        return parcelasRepository.findByPagamentoEmprestimoId(pagamentoEmprestimoId);
    }

    public Optional<Parcelas> getParcelasById(Long id) {
        return parcelasRepository.findById(id);
    }
}
