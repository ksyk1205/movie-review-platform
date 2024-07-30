package com.moviereview.application.dto;

import com.moviereview.domain.review.model.Review;
import lombok.Builder;

@Builder
public record ReviewSearchResponse(
    String id,
    String userId,
    String movieId,
    Double ranting,
    String comment
){
  public static ReviewSearchResponse from(Review review){
    return ReviewSearchResponse.builder()
        .id(review.getId())
        .userId(review.getUserId())
        .movieId(review.getMovieId())
        .ranting(review.getRating())
        .comment(review.getComment())
        .build();
  }
}
