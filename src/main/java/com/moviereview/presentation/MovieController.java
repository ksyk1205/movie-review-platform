package com.moviereview.presentation;

import com.moviereview.application.dto.MovieCreateRequest;
import com.moviereview.application.dto.MovieCreateResponse;
import com.moviereview.application.dto.MovieSearchResponse;
import com.moviereview.application.dto.MovieUpdateRequest;
import com.moviereview.domain.movie.service.MovieService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/movie")
@RequiredArgsConstructor
public class MovieController {
  private final MovieService movieService;

  @GetMapping
  public ResponseEntity<List<MovieSearchResponse>> getList() {
    return ResponseEntity.ok(movieService.getList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<MovieSearchResponse> getMovie(@PathVariable String id) {
    return ResponseEntity.ok(movieService.getMovie(id));
  }

  @PostMapping
  public ResponseEntity<MovieCreateResponse> addMovie(
      @Valid @RequestBody MovieCreateRequest movie) {
    return ResponseEntity.ok(movieService.addMovie(movie));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<MovieSearchResponse> updateMovie(
      @PathVariable String id, @RequestBody MovieUpdateRequest movie) {
    return ResponseEntity.ok(movieService.updateMovie(id, movie));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removeMovie(@PathVariable String id) {
    movieService.removeMovie(id);
    return ResponseEntity.noContent().build();
  }

}
