package com.moviereview.domain.movie.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import com.moviereview.common.annotation.AcceptanceTest;
import com.moviereview.domain.movie.model.Movie;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


@AcceptanceTest
@DisplayName("영화 기능 인수 테스트")
class MovieAcceptanceTest {

  @LocalServerPort
  int port;

  @BeforeEach
  public void environmentSetUp() {
    RestAssured.port = port;
  }

  @Test
  @DisplayName("영화를 등록할 수 있다.")
  void 영화_등록_테스트() {
    //given

    //when
    ExtractableResponse<Response> 영화_생성_결과 = MovieSteps.영화_생성_요청("원더랜드", "김태용",
        LocalDate.of(2024, 06, 30), List.of("탕웨이", "수지", "박보검"), "DRAMA");

    //then
    String id = 영화_생성_결과.jsonPath().getString("id");
    assertThat(id).isNotBlank();
  }

  @Test
  @DisplayName("영화를 수정할 수 있다.")
  void 영화_수정_테스트() {
    //given
    ExtractableResponse<Response> 영화_생성_결과 = MovieSteps.영화_생성_요청("원더랜드", "김태용",
        LocalDate.of(2024, 06, 30), List.of("탕웨이", "수지", "박보검"), "DRAMA");

    String id = 영화_생성_결과.jsonPath().getString("id");
    //when
    String title = "원더랜드2";
    ExtractableResponse<Response> 영화_수정_결과 = MovieSteps.영화_수정_요청(id, title);

    //then
    assertThat(영화_수정_결과.jsonPath().getString("title")).equals(title);
  }

  @Test
  @DisplayName("영화 리스트를 조회할 수 있다.")
  void 영화_리스트_조회_테스트() {
    //given
    ExtractableResponse<Response> 영화_생성_결과 = MovieSteps.영화_생성_요청("원더랜드", "김태용",
        LocalDate.of(2024, 06, 30), List.of("탕웨이", "수지", "박보검"), "DRAMA");

    // when
    ExtractableResponse<Response> 영화_조회_결과 = MovieSteps.영화_리스트_조회_요청();

    // then
    assertThat(영화_조회_결과.jsonPath().getList("title").get(0)).isEqualTo("원더랜드");
  }

  @Test
  @DisplayName("영화를 삭제할 수 있다.")
  void 영화_삭제_테스트() {
    //given
    ExtractableResponse<Response> 영화_생성_결과 = MovieSteps.영화_생성_요청("원더랜드", "김태용",
        LocalDate.of(2024, 06, 30), List.of("탕웨이", "수지", "박보검"), "DRAMA");
    String id = 영화_생성_결과.jsonPath().get("id");

    //when
    ExtractableResponse<Response> 영화_삭제_결과 = MovieSteps.영화_삭제_요청(id);

    //then
    assertThat(영화_삭제_결과.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
  }
}
