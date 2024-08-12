package br.com.fotoexpress.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FormalizacaoException extends RuntimeException {

  private final HttpStatus status;

  public FormalizacaoException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
