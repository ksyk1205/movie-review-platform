package com.moviereview.common.exception;

public class NotFoundException extends BusinessException{
  public NotFoundException(ErrorCode errorCode, String description) {
    super(errorCode, description);
  }
}
