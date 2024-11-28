package com.banco.sistema_banco.resources;

import com.banco.sistema_banco.services.ClientService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.sistema_banco.dto.ClientCreateDTO;
import com.banco.sistema_banco.dto.ClientUpdateDTO;
import com.banco.sistema_banco.entities.Client;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        List<Client> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id){
        Client obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody @Valid ClientCreateDTO dto) {
        Client newClient = service.createClient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);  // Retorna 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody @Valid ClientUpdateDTO dto) {
        Client updatedClient = service.updateClient(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedClient);
    }
}
