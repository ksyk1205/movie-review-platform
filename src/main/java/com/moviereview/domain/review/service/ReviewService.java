package com.moviereview.domain.review.service;

import static com.moviereview.common.exception.ErrorCode.BAD_REQUEST_EXCEPTION;
import static com.moviereview.common.exception.ErrorCode.NOT_FOUND_EXCEPTION;
import static com.moviereview.domain.review.model.Review.createReview;

import com.moviereview.application.dto.ReviewCreateRequest;
import com.moviereview.application.dto.ReviewSearchResponse;
import com.moviereview.common.exception.BadRequestException;
import com.moviereview.common.exception.NotFoundException;
import com.moviereview.domain.movie.model.Movie;
import com.moviereview.domain.movie.repository.MovieRepository;
import com.moviereview.domain.movie.service.MovieService;
import com.moviereview.domain.review.model.Review;
import com.moviereview.domain.review.repository.ReviewRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

  private final MovieService movieService;

  private final ReviewRepository reviewRepository;

  public ReviewSearchResponse addReview(String movieId, String userId, ReviewCreateRequest request) {
    Movie movie = movieService.findById(movieId);
    Review review = createReview(movie.getId(), userId, request.rating(), request.comment());
    Review save = reviewRepository.save(review);
    return ReviewSearchResponse.from(save);
  }

  public List<ReviewSearchResponse> searchReview(String movieId) {
    Movie movie = movieService.findById(movieId);
    return reviewRepository.findAllByMovieId(movie.getId()).stream()
        .map(review -> ReviewSearchResponse.from(review)).collect(
            Collectors.toList());
  }

  public void removeReview(String movieId, String reviewId) {
    Movie movie = movieService.findById(movieId);
    Review review = reviewRepository.findByMovieIdAndId(movie.getId(), reviewId)
        .orElseThrow(() -> new BadRequestException(BAD_REQUEST_EXCEPTION,
            "Bad Request ReviewId : " + reviewId));

    reviewRepository.delete(review);
  }
}
