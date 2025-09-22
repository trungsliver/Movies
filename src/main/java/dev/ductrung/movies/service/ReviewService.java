package dev.ductrung.movies.service;

import dev.ductrung.movies.entity.Movie;
import dev.ductrung.movies.entity.Review;
import dev.ductrung.movies.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String imdbId, String reviewBody) {
        Review review = reviewRepository.insert(new Review(reviewBody));
        System.out.println(review);

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review.getId()))
                .first();

        return review;
    }
}
