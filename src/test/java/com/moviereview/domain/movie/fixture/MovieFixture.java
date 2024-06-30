package com.moviereview.domain.movie.fixture;

import com.moviereview.domain.movie.model.Genre;
import com.moviereview.domain.movie.model.Movie;
import java.time.LocalDate;
import java.util.List;

public enum MovieFixture {

  WONDERLAND(
      "6680166bb198827db6ce8555",
      "원더랜드",
      "김태용",
      "2023-06-30",
      List.of("탕웨이", "수지", "박보검"),
      Genre.DRAMA
  );

  private final String id;
  private final String title;
  private final String director;
  private final String releaseDate;
  private final List<String> actors;
  private final Genre genre;

  MovieFixture(String id, String title, String director, String releaseDate, List<String> actors, Genre genre) {
    this.id = id;
    this.title = title;
    this.director = director;
    this.releaseDate = releaseDate;
    this.actors = actors;
    this.genre = genre;
  }

  public Movie createMovie() {
    return Movie.builder()
        .id(id)
        .title(title)
        .director(director)
        .releaseDate(LocalDate.parse(releaseDate))
        .actors(actors)
        .genre(genre)
        .build();
  }
}
