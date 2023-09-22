package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

public class ClientAccountPostDto {

    private Long id;

    private String firstName;

    private String lastName;

    @CPF
    private String cpf;

    @Email
    private String email;

    private Long statusId;

    public ClientAccountPostDto() {
    }

    public ClientAccountPostDto(Long id, String firstName, String lastName, String cpf, String email, Long statusId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
        this.statusId = statusId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
}
