package com.moviereview.domain.movie.acceptance;

import com.moviereview.common.annotation.AcceptanceTest;
import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
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
    Map<String, Object> params = new HashMap<>();
    params.put("title", "원더랜드");
    params.put("director", "김태용");
    params.put("release_date", "2023-06-30");
    params.put("actors", List.of("탕웨이", "수지", "박보검"));
    params.put("genre", "DRAMA");

    // when
    RestAssured.given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(params)
        .when().post("/v1/movie")
        // then
        .then().log().all()
        .statusCode(HttpStatus.OK.value()).extract();
  }

}
