package com.moviereview.domain.review.model;

import com.moviereview.application.dto.ReviewCreateRequest;
import com.moviereview.domain.common.BaseDocument;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "reviews")
@Builder
public class Review extends BaseDocument {
  @Id
  private String id;
  private String userId;
  private String movieId;
  private Double rating;
  private String comment;
  private List<Reaction> likes;
  private List<Reaction> dislikes;

  @Getter
  public static class Reaction {
    private String userId;
    private LocalDateTime timestamp;
  }

  public static Review createReview(String movieId, String userId, Double rating, String comment) {
    return Review.builder()
        .userId(userId)
        .movieId(movieId)
        .rating(rating)
        .comment(comment)
        .build();
  }
}
