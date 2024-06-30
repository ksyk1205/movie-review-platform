package com.moviereview.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MovieCreateResponse {
  String id;

  @Builder
  public MovieCreateResponse(String id) {
    this.id = id;
  }
}
