package com.moviereview.application.dto;

import lombok.Builder;

public record MovieCreateResponse(
    String id
) {
  @Builder
  public MovieCreateResponse(String id) {
    this.id = id;
  }
}
