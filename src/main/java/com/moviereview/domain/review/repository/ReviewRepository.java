package com.moviereview.domain.review.repository;

import com.moviereview.domain.review.model.Review;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
  List<Review> findAllByMovieId(String movieId);
}
