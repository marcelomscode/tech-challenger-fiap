package br.com.fotoexpress.pedido.model.dto;

public record PacoteDTO(
    Long id, String nome, String acompanhamento, String descricao, double valor) {}
