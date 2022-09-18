package com.dh.catalogservice.api.service;

import com.dh.catalogservice.api.model.movie.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieFeignClient movieFeignClient;

    private final Logger LOG = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    public MovieService(MovieFeignClient movieFeignClient){
        this.movieFeignClient = movieFeignClient;
    }

    public ResponseEntity<List<Movie>> findMovieByGenre(String genre) {
        LOG.info("Se va a incluir el llamado al movie-service...");
        return movieFeignClient.getMovieByGenre(genre);
    }
}
