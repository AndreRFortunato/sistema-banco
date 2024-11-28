package com.banco.sistema_banco.dto;

import java.math.BigDecimal;

public class PagamentoEmprestimoDTO {

    private Long accountId;          // ID da conta de onde o pagamento será debitado
    private Long emprestimoId;       // ID do empréstimo que será pago
    private BigDecimal paymentAmount; // Valor total a ser pago

    // Construtores
    public PagamentoEmprestimoDTO() {
    }

    public PagamentoEmprestimoDTO(Long accountId, Long emprestimoId, BigDecimal paymentAmount) {
        this.accountId = accountId;
        this.emprestimoId = emprestimoId;
        this.paymentAmount = paymentAmount;
    }

    // Getters e Setters
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getEmprestimoId() {
        return emprestimoId;
    }

    public void setEmprestimoId(Long emprestimoId) {
        this.emprestimoId = emprestimoId;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
