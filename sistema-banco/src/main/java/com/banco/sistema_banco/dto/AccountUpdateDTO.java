package com.banco.sistema_banco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AccountUpdateDTO {

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String senha;

    public AccountUpdateDTO() {
    }

    public AccountUpdateDTO(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
