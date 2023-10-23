package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class AuthenticationPostDto {

    @NotNull(message = "Field cpf shouldn't be null")
    @NotEmpty(message = "Field cpf shouldn't be empty")
    @NotBlank(message = "Field cpf shouldn't be blank")
    @CPF
    private String cpf;

    @NotNull(message = "Field password shouldn't be null")
    @NotEmpty(message = "Field password shouldn't be empty")
    @NotBlank(message = "Field password shouldn't be blank")
    private String password;

    public AuthenticationPostDto() {
    }

    public AuthenticationPostDto(String cpf, String password) {
        this.cpf = cpf;
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
