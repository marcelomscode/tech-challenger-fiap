package br.com.fotoexpress.pedido.model.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public record PedidoRequest(
    @NotNull Long idCliente,
    @NotNull List<@Min(1) Integer> idPacotes,
    @DecimalMin(value = "0.0", inclusive = false) @DecimalMax(value = "100.0") double desconto) {}
