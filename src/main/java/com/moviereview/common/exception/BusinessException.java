package com.moviereview.common.exception;

public class BusinessException extends RuntimeException {

  private final ErrorCode errorCode;
  private final String errorMessage;

  public BusinessException(ErrorCode errorCode, String errorMessage) {
    super(errorMessage);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

}
