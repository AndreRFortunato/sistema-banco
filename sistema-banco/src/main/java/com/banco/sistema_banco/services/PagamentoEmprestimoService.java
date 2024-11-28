package com.banco.sistema_banco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.sistema_banco.dto.PagamentoEmprestimoDTO;
import com.banco.sistema_banco.entities.Account;
import com.banco.sistema_banco.entities.Emprestimo;
import com.banco.sistema_banco.entities.PagamentoEmprestimo;
import com.banco.sistema_banco.entities.Parcelas;
import com.banco.sistema_banco.repositories.AccountRepository;
import com.banco.sistema_banco.repositories.EmprestimoRepository;
import com.banco.sistema_banco.repositories.PagamentoEmprestimoRepository;
import com.banco.sistema_banco.repositories.ParcelasRepository;

import jakarta.transaction.Transactional;

import java.util.Comparator;
import java.math.RoundingMode;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoEmprestimoService {

    @Autowired
    private PagamentoEmprestimoRepository pagamentoEmprestimoRepository;

    @Autowired
    private ParcelasRepository parcelasRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Transactional
    public PagamentoEmprestimo processarPagamentoEmprestimo(PagamentoEmprestimoDTO pagamentoEmprestimoDTO) {
        // Obtém os dados da conta e do empréstimo
        Optional<Account> optionalAccount = accountRepository.findById(pagamentoEmprestimoDTO.getAccountId());
        Optional<Emprestimo> optionalEmprestimo = emprestimoRepository.findById(pagamentoEmprestimoDTO.getEmprestimoId());

        if (optionalAccount.isEmpty() || optionalEmprestimo.isEmpty()) {
            throw new IllegalArgumentException("Conta ou Empréstimo não encontrados.");
        }

        Account account = optionalAccount.get();
        Emprestimo emprestimo = optionalEmprestimo.get();
        BigDecimal valorTotal = pagamentoEmprestimoDTO.getPaymentAmount();

        // Verifica o saldo da conta
        BigDecimal saldoAtual = BigDecimal.valueOf(account.getSaldo());
        if (saldoAtual.compareTo(valorTotal) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente na conta.");
        }

        // Cria a entidade PagamentoEmprestimo
        PagamentoEmprestimo pagamentoEmprestimo = new PagamentoEmprestimo();
        pagamentoEmprestimo.setAccount(account);
        pagamentoEmprestimo.setEmprestimo(emprestimo);
        pagamentoEmprestimo.setPaymentAmount(valorTotal);

        // Salva o pagamento do empréstimo
        PagamentoEmprestimo savedPagamentoEmprestimo = pagamentoEmprestimoRepository.save(pagamentoEmprestimo);

        // Atualiza as parcelas
        List<Parcelas> parcelas = parcelasRepository.findByEmprestimoId(emprestimo.getId());
        parcelas.sort(Comparator.comparing(Parcelas::getDataVencimento));

        BigDecimal valorRestante = valorTotal.setScale(2, RoundingMode.HALF_UP);

        for (Parcelas parcela : parcelas) {
            BigDecimal valorParcela = parcela.getValorParcela();

            if (valorRestante.compareTo(valorParcela) >= 0) {
                valorRestante = valorRestante.subtract(valorParcela);
                parcela.setPagamentoEmprestimo(savedPagamentoEmprestimo);
                parcelasRepository.save(parcela);
            } else {
                break;
            }
        }

        // Atualiza o saldo da conta
        BigDecimal novoSaldo = saldoAtual.subtract(valorTotal.subtract(valorRestante));
        account.setSaldo(novoSaldo.doubleValue());
        accountRepository.save(account);

        return savedPagamentoEmprestimo;
    }

    public List<PagamentoEmprestimo> getPagamentosByEmprestimoId(Long emprestimoId) {
        return pagamentoEmprestimoRepository.findByEmprestimoId(emprestimoId);
    }

    public Optional<PagamentoEmprestimo> getPagamentoEmprestimoById(Long id) {
        return pagamentoEmprestimoRepository.findById(id);
    }
}