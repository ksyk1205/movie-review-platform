package com.moviereview.domain.review.model;

import com.moviereview.domain.common.BaseDocument;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collation = "reviews")
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
}
