package dev.ductrung.movies.service;

import dev.ductrung.movies.repository.MovieRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.ductrung.movies.entity.Movie;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(ObjectId id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> getMovieByImdbId(String id) {
        return movieRepository.findMovieByImdbId(id);
    }
}
