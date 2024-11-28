package com.banco.sistema_banco.services;

import com.banco.sistema_banco.entities.Transacao;
import com.banco.sistema_banco.entities.enums.TipoTransacao;
import com.banco.sistema_banco.entities.Account;
import com.banco.sistema_banco.repositories.TransacaoRepository;
import com.banco.sistema_banco.repositories.AccountRepository;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private AccountRepository accountRepository;

    private static final Double TAXA_TRANSFERENCIA = 5.0;
    private static final Double LIMITE_DOC = 5000.0;

    // Método de débito
    @Transactional
    public void debito(Long accountId, Double valor) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        if (account.getSaldo() >= valor) {
            account.setSaldo(account.getSaldo() + valor);
            criarTransacao(account, null, valor, TipoTransacao.DEBITO);
            accountRepository.save(account);
        } else {
            throw new RuntimeException("Saldo insuficiente");
        }
    }

    // Método de saque
    @Transactional
    public void saque(Long accountId, Double valor) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        if (account.getSaldo() >= valor) {
            account.setSaldo(account.getSaldo() - valor);
            criarTransacao(account, null, valor, TipoTransacao.DEBITO);
            accountRepository.save(account);
        } else {
            throw new RuntimeException("Saldo insuficiente");
        }
    }

    // Método de PIX
    @Transactional
    public void pix(Long origemId, Long destinoId, Double valor) {
        Account origem = accountRepository.findById(origemId).orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));
        Account destino = accountRepository.findById(destinoId).orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));

        if (origem.getSaldo() >= valor) {
            origem.setSaldo(origem.getSaldo() - valor);
            destino.setSaldo(destino.getSaldo() + valor);
            criarTransacao(origem, destino, valor, TipoTransacao.PIX);
            accountRepository.save(origem);
            accountRepository.save(destino);
        } else {
            throw new RuntimeException("Saldo insuficiente para PIX");
        }
    }

    // Método de TED
    @Transactional
    public void ted(Long origemId, Long destinoId, Double valor) {
        Account origem = accountRepository.findById(origemId).orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));
        Account destino = accountRepository.findById(destinoId).orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));

        if (origem.getSaldo() >= valor) {
            origem.setSaldo(origem.getSaldo() - valor - TAXA_TRANSFERENCIA);
            destino.setSaldo(destino.getSaldo() + valor);
            criarTransacao(origem, destino, valor, TipoTransacao.TED);
            accountRepository.save(origem);
            accountRepository.save(destino);
        } else {
            throw new RuntimeException("Saldo insuficiente para TED");
        }
    }

    // Método de DOC
    @Transactional
    public void doc(Long origemId, Long destinoId, Double valor) {
        Account origem = accountRepository.findById(origemId).orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));
        Account destino = accountRepository.findById(destinoId).orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));
        if (valor > LIMITE_DOC) {
            throw new RuntimeException("O valor da transferência DOC excede o limite de 5000.0");
        }  
        if (origem.getSaldo() >= valor) {
            origem.setSaldo(origem.getSaldo() - valor - TAXA_TRANSFERENCIA);
            destino.setSaldo(destino.getSaldo() + valor);
            criarTransacao(origem, destino, valor, TipoTransacao.DOC);
            accountRepository.save(origem);
            accountRepository.save(destino);
        } else {
            throw new RuntimeException("Saldo insuficiente para DOC");
        }
    }

    // Criação da transação no banco de dados
    private void criarTransacao(Account origem, Account destino, Double valor, TipoTransacao tipo) {
        Transacao transacao = new Transacao();
        transacao.setAccOrigem(origem);
        transacao.setAccDestino(destino);
        transacao.setValor(valor);
        transacao.setTipot(tipo);
        transacao.setData(Instant.now());
        transacaoRepository.save(transacao);
    }
}
