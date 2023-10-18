package com.lucalucenak.Noxus.dtos.post;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public class ClientAccountPostDto {

    private Long id;

    @NotNull(message = "Field firstName shouldn't be null")
    @NotEmpty(message = "Field firstName shouldn't be empty")
    @NotBlank(message = "Field firstName shouldn't be blank")
    private String firstName;

    @NotNull(message = "Field lastName shouldn't be null")
    @NotEmpty(message = "Field lastName shouldn't be empty")
    @NotBlank(message = "Field lastName shouldn't be blank")
    private String lastName;

    @NotNull(message = "Field cpf shouldn't be null")
    @NotEmpty(message = "Field cpf shouldn't be empty")
    @NotBlank(message = "Field cpf shouldn't be blank")
    @CPF
    private String cpf;

    @NotNull(message = "Field email shouldn't be null")
    @NotEmpty(message = "Field email shouldn't be empty")
    @NotBlank(message = "Field email shouldn't be blank")
    @Email
    private String email;

    @NotNull(message = "Field cellphoneNumber shouldn't be null")
    @NotEmpty(message = "Field cellphoneNumber shouldn't be empty")
    @NotBlank(message = "Field cellphoneNumber shouldn't be blank")
    private String cellphoneNumber;

    @NotNull(message = "Field statusId shouldn't be null")
    private Long statusId;

    public ClientAccountPostDto() {
    }

    public ClientAccountPostDto(Long id, String firstName, String lastName, String cpf, String email, String cellphoneNumber, Long statusId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.email = email;
        this.cellphoneNumber = cellphoneNumber;
        this.statusId = statusId;
    }

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        this.cellphoneNumber = cellphoneNumber;
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
