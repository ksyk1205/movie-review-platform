package com.moviereview.domain.movie.service;

import com.moviereview.application.dto.MovieCreateRequest;
import com.moviereview.application.dto.MovieCreateResponse;
import com.moviereview.domain.movie.model.Genre;
import com.moviereview.domain.movie.model.Movie;
import com.moviereview.domain.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieService {

  private final MovieRepository movieRepository;

  @Transactional
  public MovieCreateResponse addMovie(MovieCreateRequest movieCreateRequest) {
    Movie movie = movieRepository.save(Movie.builder()
        .title(movieCreateRequest.title())
        .director(movieCreateRequest.director())
        .releaseDate(movieCreateRequest.releaseDate())
        .actors(movieCreateRequest.actors())
        .genre(Genre.valueOf(movieCreateRequest.genre().toString()))
        .build());

    return MovieCreateResponse.builder().id(movie.getId()).build();
  }
}
