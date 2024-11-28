package com.banco.sistema_banco.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banco.sistema_banco.dto.PagamentoEmprestimoDTO;
import com.banco.sistema_banco.entities.PagamentoEmprestimo;
import com.banco.sistema_banco.services.PagamentoEmprestimoService;


@RestController
@RequestMapping("/pagamentos")
public class PagamentoEmprestimoResource {

    @Autowired
    private PagamentoEmprestimoService pagamentoEmprestimoService;

    @PostMapping("/createDTO")
    public ResponseEntity<PagamentoEmprestimo> pagarEmprestimo(@RequestBody PagamentoEmprestimoDTO pagamentoEmprestimoDTO) {
        PagamentoEmprestimo pagamento = pagamentoEmprestimoService.processarPagamentoEmprestimo(pagamentoEmprestimoDTO);
        return ResponseEntity.ok(pagamento);
    }
}

