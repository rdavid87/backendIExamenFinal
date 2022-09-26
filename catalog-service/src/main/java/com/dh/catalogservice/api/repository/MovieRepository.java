package com.dh.catalogservice.api.repository;

import com.dh.catalogservice.api.model.movie.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {

    List<Movie> findByGenre(String genre);
}
