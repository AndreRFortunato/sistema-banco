package com.banco.sistema_banco.entities;

import java.io.Serializable;
import java.time.Instant;

import com.banco.sistema_banco.entities.enums.TipoTransacao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_transacao")
public class Transacao implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant data;
    private Integer tipot;
    private Double valor;
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accOrigem;

    @ManyToOne
    @JoinColumn(name = "acc_destino_id")
    private Account accDestino;

    public Transacao(){
    }

    public Transacao(Long id, Instant data, TipoTransacao tipot, Double valor, Account accOrigem, Account accDestino) {
        super();
        this.id = id;
        this.data = data;
        setTipot(tipot);
        this.valor = valor;
        this.accOrigem = accOrigem;
        this.accDestino = accDestino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public TipoTransacao getTipot() {
        return TipoTransacao.valueOf(tipot);
    }

    public void setTipot(TipoTransacao tipot) {
        if(tipot != null){
            this.tipot = tipot.getCode();
        }
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Account getAccOrigem() {
        return accOrigem;
    }

    public void setAccOrigem(Account accOrigem) {
        this.accOrigem = accOrigem;
    }

    public Account getAccDestino() {
        return accDestino;
    }

    public void setAccDestino(Account accDestino) {
        this.accDestino = accDestino;
    }      

}
