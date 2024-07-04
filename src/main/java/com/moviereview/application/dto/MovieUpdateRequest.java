package com.moviereview.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public record MovieUpdateRequest(
    Optional<String> title,
    Optional<String> director,
    Optional<List<String>> actors,
    Optional<String> genre,
    Optional<LocalDate> releaseDate
) {
  @Builder
  public MovieUpdateRequest(String title, String director, List<String> actors, String genre, LocalDate releaseDate) {
    this(
        Optional.ofNullable(title),
        Optional.ofNullable(director),
        Optional.ofNullable(actors),
        Optional.ofNullable(genre),
        Optional.ofNullable(releaseDate)
    );
  }
}

