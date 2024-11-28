package com.banco.sistema_banco.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.banco.sistema_banco.dto.ClientCreateDTO;
import com.banco.sistema_banco.dto.ClientUpdateDTO;
import com.banco.sistema_banco.entities.Client;
import com.banco.sistema_banco.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public List<Client> findAll(){
        return repository.findAll();
    }

    public Client findById(Long id){
        Optional<Client> obj = repository.findById(id);
        return obj.get();
    }

    public Client createClient(ClientCreateDTO dto) {
        Client client = new Client();
        client.setNome(dto.getNome());
        client.setCpf(dto.getCpf());
        client.setEmail(dto.getEmail());
        client.setTelefone(dto.getTelefone());
        return repository.save(client);
    }

    public Client updateClient(Long id, ClientUpdateDTO dto) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        client.setNome(dto.getNome());
        client.setEmail(dto.getEmail());
        client.setTelefone(dto.getTelefone());
        return repository.save(client);
    }
}