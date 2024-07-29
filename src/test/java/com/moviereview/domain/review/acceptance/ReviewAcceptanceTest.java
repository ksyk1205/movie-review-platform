package com.moviereview.domain.review.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import com.moviereview.common.annotation.AcceptanceTest;
import com.moviereview.domain.movie.acceptance.MovieSteps;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("리뷰 기능 인수 테스트")
public class ReviewAcceptanceTest extends AcceptanceTest {

  ExtractableResponse<Response> 영화_생성_결과;
  String movieId;

  @BeforeEach
  void before() {
    영화_생성_결과 = MovieSteps.영화_생성_요청("원더랜드", "김태용",
        LocalDate.of(2024, 6, 30), List.of("탕웨이", "수지", "박보검"), "DRAMA");
    movieId = 영화_생성_결과.jsonPath().getString("id");
  }

  @Test
  @DisplayName("리뷰를 등록할 수 있다.")
  void 리뷰_등록_테스트() {
    //given

    //when
    String comment = "나쁘지않아요.";
    double rating = 3.0;
    ExtractableResponse<Response> 리뷰_등록_결과 = ReviewSteps.리뷰_등록_요청(movieId, rating, comment);

    //then
    assertThat(리뷰_등록_결과.statusCode()).isEqualTo(HttpStatus.OK.value());
    assertThat(리뷰_등록_결과.jsonPath().getString("comment")).isEqualTo(comment);
  }

  @Test
  @DisplayName("리뷰를 조회할 수 있다.")
  void 리뷰_조회_테스트() {
    //given
    String comment = "나쁘지않아요.";
    double rating = 3.0;
    ReviewSteps.리뷰_등록_요청(movieId, rating, comment);

    //when
    ExtractableResponse<Response> 리뷰_조회_결과 = ReviewSteps.리뷰_조회_요청(movieId);

    //then
    assertThat(리뷰_조회_결과.jsonPath().getList("comment").get(0)).isEqualTo(comment);
  }

  @Test
  @DisplayName("리뷰를 삭제할 수 있다.")
  void 리뷰_삭제_테스트() {
    //given
    String comment = "나쁘지않아요.";
    double rating = 3.0;
    ExtractableResponse<Response> 리뷰_등록_결과 = ReviewSteps.리뷰_등록_요청(movieId, rating, comment);
    String id = 리뷰_등록_결과.jsonPath().get("id");

    //when
    ExtractableResponse<Response> 리뷰_삭제_결과 = ReviewSteps.리뷰_삭제_요청(movieId, id);

    //then
    assertThat(리뷰_삭제_결과.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
  }

}
