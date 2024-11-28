package com.banco.sistema_banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.sistema_banco.dto.EmprestimoDTO;
import com.banco.sistema_banco.entities.Client;
import com.banco.sistema_banco.entities.Emprestimo;
import com.banco.sistema_banco.entities.Parcelas;
import com.banco.sistema_banco.repositories.ClientRepository;
import com.banco.sistema_banco.repositories.EmprestimoRepository;
import com.banco.sistema_banco.repositories.ParcelasRepository;

import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private ParcelasRepository parcelasRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<Emprestimo> findAll(){
        return emprestimoRepository.findAll();
    }

    public Emprestimo saveEmprestimo(Emprestimo emprestimo) {
        Emprestimo savedEmprestimo = emprestimoRepository.save(emprestimo);
        gerarParcelas(savedEmprestimo);
        return savedEmprestimo;
    }

    public Emprestimo createEmprestimo(EmprestimoDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
            .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setClient(client);
        emprestimo.setAmount(dto.getAmount());
        emprestimo.setStartDate(dto.getStartDate());
        emprestimo.setEndDate(dto.getEndDate());
        
        return emprestimoRepository.save(emprestimo);
    }

    private void gerarParcelas(Emprestimo emprestimo) {
        LocalDate startDate = LocalDate.ofInstant(emprestimo.getStartDate(), ZoneId.systemDefault()).plusMonths(1);
        LocalDate endDate = LocalDate.ofInstant(emprestimo.getEndDate(), ZoneId.systemDefault());
        long numeroDeParcelas = ChronoUnit.MONTHS.between(startDate, endDate) + 1;
        BigDecimal amount = emprestimo.getAmount();
        
        for (int i = 1; i <= numeroDeParcelas; i++) {
            BigDecimal juros = BigDecimal.valueOf(0.05 + 0.01 * (i - 1));
            BigDecimal valorParcelaBase = amount.divide(BigDecimal.valueOf(numeroDeParcelas), RoundingMode.HALF_UP);
            BigDecimal valorParcela = valorParcelaBase.multiply(BigDecimal.ONE.add(juros));
            Parcelas parcelas = new Parcelas();
            parcelas.setEmprestimo(emprestimo);
            parcelas.setNumeroParcela(i);
            parcelas.setValorParcela(valorParcela);
            parcelas.setDataVencimento(startDate.plusMonths(i - 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
            parcelasRepository.save(parcelas);
        }
    }

    public List<Emprestimo> getEmprestimosByClientId(Long clientId) {
        return emprestimoRepository.findByClientId(clientId);
    }

    public Optional<Emprestimo> getEmprestimoById(Long id) {
        return emprestimoRepository.findById(id);
    }
}

