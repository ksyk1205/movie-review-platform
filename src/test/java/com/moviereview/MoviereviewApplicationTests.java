package com.moviereview;

import com.moviereview.domain.movie.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
class MoviereviewApplicationTests {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void test() {
		mongoTemplate.save(Movie.builder()
				.title("test")
				.build());
	}

	@Test
	void contextLoads() {
	}

}
