package com.banco.sistema_banco.resources;

import com.banco.sistema_banco.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacoes")
public class TransacaoResource {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/debito/{accountId}")
    public ResponseEntity<String> debito(@PathVariable Long accountId, @RequestParam Double valor) {
        try {
            transacaoService.debito(accountId, valor);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro na transação: " + e.getMessage());
        }
    }

    @PostMapping("/saque/{accountId}")
    public ResponseEntity<String> saque(@PathVariable Long accountId, @RequestParam Double valor) {
        try {
            transacaoService.saque(accountId, valor);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro na transação: " + e.getMessage());
        }
    }

    @PostMapping("/pix")
    public ResponseEntity<String> pix(@RequestParam Long origemId, @RequestParam Long destinoId, @RequestParam Double valor) {
        try {
            transacaoService.pix(origemId, destinoId, valor);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro na transação: " + e.getMessage());
        }
    }

    @PostMapping("/ted")
    public ResponseEntity<String> ted(@RequestParam Long origemId, @RequestParam Long destinoId, @RequestParam Double valor) {
        try {
            transacaoService.ted(origemId, destinoId, valor);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro na transação: " + e.getMessage());
        }
    }

    @PostMapping("/doc")
    public ResponseEntity<String> doc(@RequestParam Long origemId, @RequestParam Long destinoId, @RequestParam Double valor) {
        try {
            transacaoService.doc(origemId, destinoId, valor);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro na transação: " + e.getMessage());
        }
    }
}
