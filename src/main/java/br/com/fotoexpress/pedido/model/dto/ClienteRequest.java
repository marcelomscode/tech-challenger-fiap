package br.com.fotoexpress.pedido.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequest(
    @NotBlank(message = "O nome não pode ser vazio") String nome,
    @Email String email,
    @NotBlank(message = "O telefone não pode ser vazio") String telefone,
    @CPF(message = "CPF inválido") String cpf) {}
