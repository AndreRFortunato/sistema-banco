package com.banco.sistema_banco.resources;

import com.banco.sistema_banco.dto.ClientAccountDTO;
import com.banco.sistema_banco.services.ClientAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientAccountResource {

    @Autowired
    private ClientAccountService clientAccountService;

    @PostMapping("/clients-accounts")
    public ResponseEntity<Void> createClientAndAccount(@RequestBody ClientAccountDTO dto) {
        clientAccountService.createClientAndAccount(dto);
        return ResponseEntity.ok().build();
    }
}
