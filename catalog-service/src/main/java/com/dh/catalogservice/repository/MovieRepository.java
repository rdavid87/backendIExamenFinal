package com.dh.catalogservice.repository;

import com.dh.catalogservice.api.model.movie.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String > {

    List<Movie> findByGenre(String genre);
}
