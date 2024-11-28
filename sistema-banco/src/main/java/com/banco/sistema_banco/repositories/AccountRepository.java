package com.banco.sistema_banco.repositories;

import com.banco.sistema_banco.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{

    
}
