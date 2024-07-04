package com.moviereview.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public record MovieSearchResponse(
  String id,
  String title,
  String director,
  List<String> actors,
  String genre,
  LocalDate releaseDate,
  LocalDateTime createdAt
){

  @Builder
  public MovieSearchResponse(String id, String title, String director, List<String> actors,
      String genre, LocalDate releaseDate, LocalDateTime createdAt) {
    this.id = id;
    this.title = title;
    this.director = director;
    this.actors = actors;
    this.genre = genre;
    this.releaseDate = releaseDate;
    this.createdAt = createdAt;
  }
}
