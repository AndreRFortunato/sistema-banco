package com.banco.sistema_banco.services;

import com.banco.sistema_banco.dto.AccountCreateDTO;
import com.banco.sistema_banco.dto.AccountUpdateDTO;
import com.banco.sistema_banco.entities.Account;
import com.banco.sistema_banco.entities.Client;
import com.banco.sistema_banco.repositories.AccountRepository;
import com.banco.sistema_banco.repositories.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account findById(Long id){
        Optional<Account> obj = accountRepository.findById(id);
        return obj.get();
    }

    public Account createAccount(AccountCreateDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        Account account = new Account();
        account.setNumeroConta(dto.getNumeroConta());
        account.setTipo(dto.getTipo());
        account.setSaldo(dto.getSaldo());
        account.setClient(client);

        return accountRepository.save(account);
    }

    public Account updateAccount(Long accountId, AccountUpdateDTO dto) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));

        account.setSenha(dto.getSenha());

        return accountRepository.save(account);
    }
}