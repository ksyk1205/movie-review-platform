package com.moviereview.application.dto;

import com.moviereview.domain.movie.model.Movie;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record MovieSearchResponse(
  String id,
  String title,
  String director,
  List<String> actors,
  String genre,
  LocalDate releaseDate,
  LocalDateTime createdAt
){
  public static MovieSearchResponse from(Movie movie) {
    return MovieSearchResponse.builder()
        .id(movie.getId())
        .title(movie.getTitle())
        .director(movie.getDirector())
        .genre(movie.getGenre().toString())
        .actors(movie.getActors())
        .releaseDate(movie.getReleaseDate())
        .createdAt(movie.getCreatedAt())
        .build();
  }
}
