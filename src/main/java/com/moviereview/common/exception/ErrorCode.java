package com.moviereview.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST),
  NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND);

  private final HttpStatus httpStatus;
}
