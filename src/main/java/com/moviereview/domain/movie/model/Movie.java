package com.moviereview.domain.movie.model;

import com.moviereview.domain.common.BaseDocument;
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
public class Movie extends BaseDocument {

  @Id
  private String id;
  private String title;
  private String director;
  private List<String> actors;
  private Genre genre;
  @Field("release_date")
  private LocalDate releaseDate;

  @Builder
  public Movie(String id, String title, String director, List<String> actors, Genre genre,
      LocalDate releaseDate) {
    this.id = id;
    this.title = title;
    this.director = director;
    this.actors = actors;
    this.genre = genre;
    this.releaseDate = releaseDate;
  }

  public void updateTitle(String title) {
    this.title = title;
  }

  public void updateDirector(String director) {
    this.director = director;
  }

  public void updateReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public void updateActors(List<String> actors) {
    this.actors = actors;
  }

  public void updateGenre(Genre genre) {
    this.genre =genre;
  }
}
