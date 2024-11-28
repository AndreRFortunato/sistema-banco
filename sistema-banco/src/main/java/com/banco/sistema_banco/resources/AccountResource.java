package com.banco.sistema_banco.resources;

import com.banco.sistema_banco.dto.AccountCreateDTO;
import com.banco.sistema_banco.dto.AccountUpdateDTO;
import com.banco.sistema_banco.entities.Account;
import com.banco.sistema_banco.services.AccountService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/accounts")
public class AccountResource {

    @Autowired
    private AccountService service;

    @GetMapping
    public ResponseEntity<List<Account>> findAll() {
        List<Account> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id){
        Account obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody @Valid AccountCreateDTO dto) {
        Account newAccount = service.createAccount(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);  // Retorna 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody @Valid AccountUpdateDTO dto) {
        Account updatedAccount = service.updateAccount(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedAccount);
    }
}
