package com.moviereview.domain.review.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import com.moviereview.common.annotation.AcceptanceTest;
import com.moviereview.domain.movie.acceptance.MovieSteps;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("리뷰 기능 인수 테스트")
public class ReviewAcceptanceTest extends AcceptanceTest {

  @Test
  @DisplayName("리뷰를 등록할 수 있다.")
  void 리뷰_등록_테스트() {
    //given
    ExtractableResponse<Response> 영화_생성_결과 = MovieSteps.영화_생성_요청("원더랜드", "김태용",
        LocalDate.of(2024, 06, 30), List.of("탕웨이", "수지", "박보검"), "DRAMA");

    //when
    String id = 영화_생성_결과.jsonPath().getString("id");
    String comment = "나쁘지않아요.";
    ExtractableResponse<Response> 리뷰_등록_결과 = ReviewSteps.리뷰_등록_요청(id, 3.0, comment);

    //then
    assertThat(리뷰_등록_결과.jsonPath().getString("comment")).isEqualTo(comment);
  }

}
