package com.moviereview.domain.movie.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MovieSteps {

  public static ExtractableResponse<Response> 영화_생성_요청(String title, String director,
      LocalDate releaseDate, List<String> actors, String genre) {

    Map<String, Object> params = new HashMap<>();
    params.put("title", title);
    params.put("director", director);
    params.put("release_date", releaseDate);
    params.put("actors", actors);
    params.put("genre", genre);

    return RestAssured.given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(params)
        .when().post("/v1/movie")
        .then().log().all()
        .statusCode(HttpStatus.OK.value()).extract();
  }

  public static ExtractableResponse<Response> 영화_수정_요청(String id, String title) {
    Map<String, Object> params = new HashMap<>();
    params.put("title", title);

    return RestAssured.given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(params)
        .when().patch("/v1/movie/" + id)
        .then().log().all()
        .statusCode(HttpStatus.OK.value()).extract();
  }

  public static ExtractableResponse<Response> 영화_리스트_조회_요청() {
    return RestAssured.given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when().get("/v1/movie")
        .then().log().all()
        .statusCode(HttpStatus.OK.value()).extract();
  }

  public static ExtractableResponse<Response> 영화_삭제_요청(String id) {
    return RestAssured.given().log().all()
        .when().delete("/v1/movie/" + id)
        .then().log().all()
        .extract();
  }

}
