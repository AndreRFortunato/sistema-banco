package com.banco.sistema_banco.services;

import com.banco.sistema_banco.dto.ClientAccountDTO;
import com.banco.sistema_banco.entities.Client;
import com.banco.sistema_banco.entities.Account;
import com.banco.sistema_banco.repositories.ClientRepository;
import com.banco.sistema_banco.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientAccountService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void createClientAndAccount(ClientAccountDTO dto) {
        // Criar o Client
        Client client = new Client();
        client.setNome(dto.getClientName());
        client.setCpf(dto.getCpf());
        client.setEmail(dto.getClientEmail());
        client.setTelefone(dto.getTelefone());

        // Salva o Client no banco
        Client savedClient = clientRepository.save(client);

        // Criar a Account associada ao Client
        Account account = new Account();
        account.setNumeroConta(dto.getAccountNumber());
        account.setTipo(dto.getTipo());
        account.setSaldo(dto.getInitialBalance());
        account.setClient(savedClient);  // Associação com o Client recém-criado

        // Salva a Account no banco
        accountRepository.save(account);
    }
}
