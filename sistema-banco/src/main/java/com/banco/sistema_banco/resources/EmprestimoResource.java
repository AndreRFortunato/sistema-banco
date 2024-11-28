package com.banco.sistema_banco.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banco.sistema_banco.dto.EmprestimoDTO;
import com.banco.sistema_banco.entities.Client;
import com.banco.sistema_banco.entities.Emprestimo;
import com.banco.sistema_banco.services.EmprestimoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoResource {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping
    public ResponseEntity<List<Emprestimo>> findAll() {
        List<Emprestimo> list = emprestimoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<List<Emprestimo>> getEmprestimosByClientId(@PathVariable Long clientId) {
        List<Emprestimo> emprestimos = emprestimoService.getEmprestimosByClientId(clientId);
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> getEmprestimoById(@PathVariable Long id) {
        Optional<Emprestimo> emprestimo = emprestimoService.getEmprestimoById(id);
        return emprestimo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createDTO")
    public ResponseEntity<Emprestimo> createEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setClient(new Client(emprestimoDTO.getClientId()));
        emprestimo.setAmount(emprestimoDTO.getAmount());
        emprestimo.setStartDate(emprestimoDTO.getStartDate());
        emprestimo.setEndDate(emprestimoDTO.getEndDate());
        
        Emprestimo savedEmprestimo = emprestimoService.saveEmprestimo(emprestimo);
        return ResponseEntity.ok(savedEmprestimo);
    }
}

