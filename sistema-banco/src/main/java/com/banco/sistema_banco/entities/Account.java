package com.banco.sistema_banco.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.banco.sistema_banco.entities.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_account")
public class Account implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroConta;
    private String senha;
    private Integer tipo;
    private Double saldo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "account")
    private List<PagamentoEmprestimo> PagamentoEmprestimo = new ArrayList<>();

    public Account(){
        this.senha = gerarSenhaAleatoria();
        this.saldo = 0.0;
    }

    public Account(Long id) {
        this.id = id;
        this.senha = gerarSenhaAleatoria();
        this.saldo = 0.0;
    }

    public Account(Long id, String numeroConta, Tipo tipo, Double saldo, Client client) {
        super();
        this.id = id;
        this.numeroConta = numeroConta;
        this.senha = gerarSenhaAleatoria();
        setTipo(tipo);
        this.saldo = (saldo != null) ? saldo : 0.0;
        this.client = client;
    }

    private String gerarSenhaAleatoria() {
        Random random = new Random();
        int senha = 1000 + random.nextInt(9000); // Gera um número entre 1000 e 9999
        return String.valueOf(senha);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Tipo getTipo() {
        return Tipo.valueOf(tipo);
    }

    public void setTipo(Tipo tipo) {
        if(tipo != null){
            this.tipo = tipo.getCode();
        }
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
        Account other = (Account) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
