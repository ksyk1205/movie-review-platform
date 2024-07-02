package com.moviereview.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public record MovieListResponse(
  String id,
  String title,
  List<String> actors,
  String genre,
  LocalDate releaseDate,
  LocalDateTime createdAt
){

  @Builder
  public MovieListResponse(String id, String title, List<String> actors, String genre,
      LocalDate releaseDate, LocalDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.actors = actors;
    this.genre = genre;
    this.releaseDate = releaseDate;
    this.createdAt = createdAt;
  }
}
