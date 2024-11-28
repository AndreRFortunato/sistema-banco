package com.banco.sistema_banco.dto;

import java.io.Serializable;

import com.banco.sistema_banco.entities.enums.Tipo;

public class ClientAccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    // Campos para Client
    private String clientName;
    private String cpf;
    private String clientEmail;
    private String telefone;

    // Campos para Account
    private String accountNumber;
    private Integer tipo;
    private Double initialBalance;

    public ClientAccountDTO() {
    }

    public ClientAccountDTO(String clientName, String cpf, String clientEmail, String telefone, String accountNumber,
        Tipo tipo, Double initialBalance) {
        this.clientName = clientName;
        this.cpf = cpf;
        this.clientEmail = clientEmail;
        this.telefone = telefone;
        this.accountNumber = accountNumber;
        setTipo(tipo);
        this.initialBalance = initialBalance;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Tipo getTipo() {
        return Tipo.valueOf(tipo);
    }

    public void setTipo(Tipo tipo) {
        if(tipo != null){
            this.tipo = tipo.getCode();
        }
    }
}
