package com.moviereview.domain.review.service;

import static com.moviereview.common.exception.ErrorCode.NOT_FOUND_EXCEPTION;
import static com.moviereview.domain.review.model.Review.createReview;

import com.moviereview.application.dto.ReviewCreateRequest;
import com.moviereview.common.exception.BadRequestException;
import com.moviereview.common.exception.NotFoundException;
import com.moviereview.domain.movie.model.Movie;
import com.moviereview.domain.movie.repository.MovieRepository;
import com.moviereview.domain.review.model.Review;
import com.moviereview.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
  private final MovieRepository movieRepository;

  private final ReviewRepository reviewRepository;

  public void addReview(String movieId, String userId, ReviewCreateRequest request) {
    Movie movie = movieRepository.findById(movieId)
        .orElseThrow(() -> new NotFoundException(NOT_FOUND_EXCEPTION,"Movie not found with id" + movieId));


    Review review = createReview(movie.getId(), userId, request);
    reviewRepository.save(review);
  }

}
