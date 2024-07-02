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
    ExtractableResponse<Response> 영화_생성_response = MovieSteps.영화_생성_요청("원더랜드", "김태용",
        LocalDate.of(2024, 06, 30), List.of("탕웨이", "수지", "박보검"), "DRAMA");

    //then
    String id = 영화_생성_response.jsonPath().getString("id");
    assertThat(id).isNotBlank();
  }

  @Test
  @DisplayName("영화 리스트를 조회할 수 있다.")
  void 영화_리스트_조회() {
    //given
    ExtractableResponse<Response> 영화_생성_response = MovieSteps.영화_생성_요청("원더랜드", "김태용",
        LocalDate.of(2024, 06, 30), List.of("탕웨이", "수지", "박보검"), "DRAMA");

    // when
    ExtractableResponse<Response> response = RestAssured.given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when().get("/v1/movie")

    // then
        .then().log().all()
        .statusCode(HttpStatus.OK.value()).extract();

   assertThat(response.jsonPath().getList("title").get(0)).isEqualTo("원더랜드");
  }
}
