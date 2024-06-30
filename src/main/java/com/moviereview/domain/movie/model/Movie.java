package com.moviereview.domain.movie.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Document(collection = "movies")
public class Movie {
  @Id
  private String id;
  private String title;
  private String director;
  private List<String> actors;
  private Genre genre;
  @Field("release_date")
  private LocalDate releaseDate;
  @CreatedDate
  @Field("created_at")
  private LocalDateTime createdAt;
  @LastModifiedDate
  @Field("updated_at")
  private LocalDateTime updatedAt;

  @Builder
  public Movie(String id, String title, String director, List<String> actors, Genre genre,
      LocalDate releaseDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.title = title;
    this.director = director;
    this.actors = actors;
    this.genre = genre;
    this.releaseDate = releaseDate;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
