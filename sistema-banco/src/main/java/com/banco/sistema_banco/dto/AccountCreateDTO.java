package com.banco.sistema_banco.dto;

import com.banco.sistema_banco.entities.enums.Tipo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AccountCreateDTO {

    @NotBlank(message = "O número da conta é obrigatório")
    @Size(min = 3, max = 20, message = "O número da conta deve ter entre 5 e 20 caracteres")
    private String numeroConta;

    @NotNull(message = "O tipo de conta é obrigatório")
    private Integer tipo;

    @NotNull(message = "O saldo inicial é obrigatório")
    private Double saldo;

    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clientId;

    public AccountCreateDTO() {
    }

    public AccountCreateDTO(String numeroConta, Tipo tipo, Double saldo, Long clientId) {
        this.numeroConta = numeroConta;
        setTipo(tipo);
        this.saldo = saldo;
        this.clientId = clientId;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
