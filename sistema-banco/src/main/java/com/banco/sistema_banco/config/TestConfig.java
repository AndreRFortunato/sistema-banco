package com.banco.sistema_banco.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import java.time.Instant;
import java.util.Arrays;

import com.banco.sistema_banco.entities.Account;
import com.banco.sistema_banco.entities.Client;
import com.banco.sistema_banco.entities.enums.Tipo;
import com.banco.sistema_banco.repositories.AccountRepository;
import com.banco.sistema_banco.repositories.ClientRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
        Client c1 = new Client(null, "Andre", "43020238803", "rua Bahia", "988382379", "andre@gmail.com", Instant.now());
        Client c2 = new Client(null, "Orlando", "07842364821", "rua Geraldo", "985715689", "orlando@gmail.com", Instant.now());
        clientRepository.saveAll(Arrays.asList(c1, c2));
    

        Account a1 = new Account(null, "001",  Tipo.CONTA_POUPANÇA, 15000.0, c1);
        Account a2 = new Account(null, "002",  Tipo.CONTA_DE_PAGAMENTO, 2000.0, c2);
        Account a3 = new Account(null, "003",  Tipo.CONTA_DE_PAGAMENTO, 3000.0, c1);
        accountRepository.saveAll(Arrays.asList(a1, a2, a3));

    }
}
