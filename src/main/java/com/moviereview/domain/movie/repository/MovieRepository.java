package com.moviereview.domain.movie.repository;

import com.moviereview.domain.movie.model.Movie;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
  @Query("{ '$or': [ { 'title': { '$regex': ?0, '$options': 'i' } }, { 'director': { '$regex': ?0, '$options': 'i' } }, { 'actors': { '$regex': ?0, '$options': 'i' } } ] }")
  List<Movie> searchByTitleOrDirectorOrActors(String searchTerm);
}
