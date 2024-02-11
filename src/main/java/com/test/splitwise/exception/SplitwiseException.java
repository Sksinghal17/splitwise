package com.test.splitwise.exception;

public class SplitwiseException extends RuntimeException {

  public SplitwiseException() {
    super();
  }

  public SplitwiseException(String message) {
    super(message);
  }

  public SplitwiseException(String message, Throwable cause) {
    super(message, cause);
  }

  public SplitwiseException(Throwable cause) {
    super(cause);
  }
}

