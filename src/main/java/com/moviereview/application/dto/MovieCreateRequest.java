package com.moviereview.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

public record MovieCreateRequest(
    @NotBlank(message = "Title cannot be blank")
    String title,
    @NotBlank(message = "Director cannot be blank")
    String director,
    @NotEmpty(message = "Actors list cannot be empty")
    List<@NotBlank(message = "Actor name cannot be blank") String> actors,
    @NotBlank(message = "Genre cannot be blank")
    String genre,
    @NotNull(message = "Release date cannot be null")
    LocalDate releaseDate
) {

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


