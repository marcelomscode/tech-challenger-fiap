package br.com.fotoexpress.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PedidoException extends RuntimeException {

  private final HttpStatus status;

  public PedidoException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
