package com.moviereview.domain.movie.service;

import com.moviereview.application.dto.MovieCreateRequest;
import com.moviereview.application.dto.MovieCreateResponse;
import com.moviereview.application.dto.MovieSearchResponse;
import com.moviereview.application.dto.MovieUpdateRequest;
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

  @Transactional
  public MovieSearchResponse updateMovie(String id, MovieUpdateRequest movieUpdateRequest) {
    Movie movie = findById(id);

    movieUpdateRequest.title().ifPresent(movie::updateTitle);
    movieUpdateRequest.director().ifPresent(movie::updateDirector);
    movieUpdateRequest.actors().ifPresent(movie::updateActors);
    movieUpdateRequest.genre().ifPresent(genre -> movie.updateGenre(Genre.valueOf(genre)));
    movieUpdateRequest.releaseDate().ifPresent(movie::updateReleaseDate);

    Movie updatedMovie = movieRepository.save(movie);

    return MovieSearchResponse.builder()
        .id(updatedMovie.getId())
        .title(updatedMovie.getTitle())
        .director(updatedMovie.getDirector())
        .releaseDate(updatedMovie.getReleaseDate())
        .actors(updatedMovie.getActors())
        .genre(updatedMovie.getGenre().toString())
        .build();
  }

  public List<MovieSearchResponse> getList() {
    return movieRepository.findAll().stream().map(movie ->
        MovieSearchResponse.builder()
            .id(movie.getId())
            .title(movie.getTitle())
            .director(movie.getDirector())
            .genre(movie.getGenre().toString())
            .actors(movie.getActors())
            .releaseDate(movie.getReleaseDate())
            .createdAt(movie.getCreatedAt())
            .build()).collect(Collectors.toList());
  }

  @Transactional
  public void removeMovie(String id) {
    findById(id);
    movieRepository.deleteById(id);
  }

  private Movie findById(String id) {
    return movieRepository.findById(id).orElseThrow(
        () -> new BadRequestException(ErrorCode.BAD_REQUEST_EXCEPTION, "movie resource not found"));

  }
}
