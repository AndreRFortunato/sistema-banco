package com.banco.sistema_banco.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banco.sistema_banco.entities.Parcelas;
import com.banco.sistema_banco.services.ParcelasService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parcelas")
public class ParcelasResource {

    @Autowired
    private ParcelasService parcelasService;

    @GetMapping
    public ResponseEntity<List<Parcelas>> findAll() {
        List<Parcelas> list = parcelasService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Parcelas> createParcela(@RequestBody Parcelas parcela) {
        Parcelas savedParcela = parcelasService.saveParcela(parcela);
        return ResponseEntity.ok(savedParcela);
    }

    @GetMapping("/emprestimos/{emprestimoId}")
    public ResponseEntity<List<Parcelas>> getParcelasByEmprestimoId(@PathVariable Long emprestimoId) {
        List<Parcelas> parcelas = parcelasService.getParcelasByEmprestimoId(emprestimoId);
        return ResponseEntity.ok(parcelas);
    }

    @GetMapping("/pagamentos/{pagamentoEmprestimoId}")
    public ResponseEntity<List<Parcelas>> getParcelasByPagamentoEmprestimoId(@PathVariable Long pagamentoEmprestimoId) {
        List<Parcelas> parcelas = parcelasService.getParcelasByPagamentoEmprestimoId(pagamentoEmprestimoId);
        return ResponseEntity.ok(parcelas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parcelas> getParcelaById(@PathVariable Long id) {
        Optional<Parcelas> parcelas = parcelasService.getParcelasById(id);
        return parcelas.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
