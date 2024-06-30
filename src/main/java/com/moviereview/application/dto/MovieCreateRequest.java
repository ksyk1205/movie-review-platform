package com.moviereview.application.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

public record MovieCreateRequest(
    String title,
    String director,
    List<String> actors,
    String genre,
    LocalDate releaseDate
){

  @Builder
  public MovieCreateRequest(String title, String director, List<String> actors, String genre,
      LocalDate releaseDate) {
    this.title = title;
    this.director = director;
    this.actors = actors;
    this.genre = genre;
    this.releaseDate = releaseDate;
  }
}


