package com.moviereview.common.exception;

public class BadRequestException extends BusinessException{
  public BadRequestException(ErrorCode errorCode, String description) {
    super(errorCode, description);
  }
}
