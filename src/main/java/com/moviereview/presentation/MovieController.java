package com.moviereview.presentation;

import com.moviereview.application.dto.MovieCreateRequest;
import com.moviereview.application.dto.MovieCreateResponse;
import com.moviereview.domain.movie.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/movie")
@RequiredArgsConstructor
public class MovieController {
  private final MovieService movieService;

  @PostMapping
  public ResponseEntity<MovieCreateResponse> addMovie(@Valid @RequestBody MovieCreateRequest movie) {
    return ResponseEntity.ok(movieService.addMovie(movie));
  }
}
