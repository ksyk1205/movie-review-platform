package com.moviereview.domain.movie.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.moviereview.application.dto.MovieCreateRequest;
import com.moviereview.application.dto.MovieCreateResponse;
import com.moviereview.application.dto.MovieListResponse;
import com.moviereview.domain.movie.fixture.MovieFixture;
import com.moviereview.domain.movie.model.Genre;
import com.moviereview.domain.movie.model.Movie;
import com.moviereview.domain.movie.repository.MovieRepository;
import com.moviereview.domain.movie.service.MovieService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("영화 서비스 단위 테스트 with Mock")
@ExtendWith(MockitoExtension.class)
public class MovieServiceMockTest {

  @Mock
  private MovieRepository movieRepository;
  @InjectMocks
  private MovieService movieService;

  @Test
  @DisplayName("영화 등록 테스트 ")
  void addMovieTest() {
    //given
    Movie movie = MovieFixture.WONDERLAND.createMovie();
    when(movieRepository.save(any())).thenReturn(movie);

    //when
    MovieCreateRequest movieCreateRequest = MovieCreateRequest.builder()
        .title("원더랜드")
        .director("김태용")
        .releaseDate(LocalDate.parse("2023-06-30"))
        .actors(List.of("탕웨이", "수지", "박보검"))
        .genre(String.valueOf(Genre.DRAMA))
        .build();
    MovieCreateResponse movieCreateResponse = movieService.addMovie(movieCreateRequest);

    //then
    assertThat(movieCreateResponse.id()).isEqualTo(movie.getId());
  }

  @Test
  @DisplayName("영화 리스트 조회 테스트")
  void getListMovieTest() {
    //given
    Movie movie = MovieFixture.WONDERLAND.createMovie();
    when(movieRepository.findAll()).thenReturn(List.of(movie));

    //when
    List<MovieListResponse> list = movieService.getList();

    assertAll(
        () -> assertThat(list.size()).isEqualTo(1),
        () -> assertThat(list.get(0).title()).isEqualTo(movie.getTitle()),
        () -> assertThat(list.get(0).releaseDate()).isEqualTo(movie.getReleaseDate())
    );
  }
}
