package com.banco.sistema_banco.dto;

import java.math.BigDecimal;
import java.time.Instant;

public class EmprestimoDTO {

    private Long clientId;
    private BigDecimal amount;
    private Instant startDate;
    private Instant endDate;
    
    public EmprestimoDTO() {
    }
    
    public EmprestimoDTO(Long clientId, BigDecimal amount, Instant startDate, Instant endDate) {
        this.clientId = clientId;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }
}
