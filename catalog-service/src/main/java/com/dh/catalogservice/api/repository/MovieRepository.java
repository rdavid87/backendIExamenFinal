package com.dh.catalogservice.api.repository;

import com.dh.catalogservice.api.model.movie.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {
}
