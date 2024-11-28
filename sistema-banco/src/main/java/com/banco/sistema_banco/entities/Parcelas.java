package com.banco.sistema_banco.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Transient;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class Parcelas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id")
    private Emprestimo emprestimo;
    
    @ManyToOne
    @JoinColumn(name = "pagamento_emprestimo_id")
    private PagamentoEmprestimo pagamentoEmprestimo;
    
    private Integer numeroParcela;
    private BigDecimal valorParcela;
    private Instant dataVencimento;

    @Transient
    private BigDecimal taxaDeJuros;

    @PostLoad
    @PostPersist
    public void calcularTaxaDeJuros() {
        this.taxaDeJuros = BigDecimal.valueOf(0.05 + 0.01 * (this.numeroParcela - 1));
    }
    
    public Parcelas() {
    }

    public Parcelas(Long id, Emprestimo emprestimo, Integer numeroParcela, BigDecimal valorParcela, Instant dataVencimento) {
        this.id = id;
        this.emprestimo = emprestimo;
        this.numeroParcela = numeroParcela;
        this.valorParcela = valorParcela;
        this.dataVencimento = dataVencimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public Integer getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(Integer numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Instant getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Instant dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setPagamentoEmprestimo(PagamentoEmprestimo pagamentoEmprestimo) {
        this.pagamentoEmprestimo = pagamentoEmprestimo;
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
        Parcelas other = (Parcelas) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }    
}
