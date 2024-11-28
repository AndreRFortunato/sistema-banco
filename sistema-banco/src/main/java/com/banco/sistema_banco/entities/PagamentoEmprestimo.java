package com.banco.sistema_banco.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class PagamentoEmprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    
    @ManyToOne
    @JoinColumn(name = "emprestimo_id")
    private Emprestimo emprestimo;
    
    private BigDecimal paymentAmount;
    
    public PagamentoEmprestimo() {
    }

    public PagamentoEmprestimo(Long id, Account account, Emprestimo emprestimo, BigDecimal paymentAmount) {
        this.id = id;
        this.account = account;
        this.emprestimo = emprestimo;
        this.paymentAmount = paymentAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @PrePersist
    @PreUpdate
    public void atualizarSaldoConta() {
        if (account != null && paymentAmount != null) {
            account.setSaldo(account.getSaldo() - paymentAmount.doubleValue());
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PagamentoEmprestimo other = (PagamentoEmprestimo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
