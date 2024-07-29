package com.moviereview.domain.review.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class ReviewSteps {
  public static ExtractableResponse<Response> 리뷰_등록_요청(String movieId, Double rating, String comment){
    Map<String, Object> params = new HashMap<>();
    params.put("rating", rating);
    params.put("comment", comment);

    return RestAssured.given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(params)
        .when().post("/v1/movie/"+movieId+"/reviews")
        .then().log().all()
        .statusCode(HttpStatus.CREATED.value()).extract();
  }

  public static ExtractableResponse<Response> 리뷰_조회_요청(String moveId) {
    return RestAssured.given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when().get("/v1/movie/"+moveId+"/reviews")
        .then().log().all()
        .statusCode(HttpStatus.OK.value()).extract();
  }
}
