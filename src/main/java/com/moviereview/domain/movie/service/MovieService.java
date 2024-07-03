package com.moviereview.domain.movie.service;

import com.moviereview.application.dto.MovieCreateRequest;
import com.moviereview.application.dto.MovieCreateResponse;
import com.moviereview.application.dto.MovieListResponse;
import com.moviereview.common.exception.BadRequestException;
import com.moviereview.common.exception.ErrorCode;
import com.moviereview.domain.movie.model.Genre;
import com.moviereview.domain.movie.model.Movie;
import com.moviereview.domain.movie.repository.MovieRepository;
import java.util.List;
import java.util.stream.Collectors;
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

  public List<MovieListResponse> getList() {
    return movieRepository.findAll().stream().map(movie ->
        MovieListResponse.builder()
            .id(movie.getId())
            .title(movie.getTitle())
            .genre(movie.getGenre().toString())
            .actors(movie.getActors())
            .releaseDate(movie.getReleaseDate())
            .createdAt(movie.getCreatedAt())
            .build()).collect(Collectors.toList());
  }

  public void removeMovie(String id) {
    movieRepository.findById(id).orElseThrow(
        () -> new BadRequestException(ErrorCode.BAD_REQUEST_EXCEPTION, "movie resource not found"));

    movieRepository.deleteById(id);
  }
}
