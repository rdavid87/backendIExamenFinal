package com.dh.catalogservice.api.service;

import com.dh.catalogservice.api.model.movie.Movie;
import com.dh.catalogservice.config.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "movie-service")
@LoadBalancerClient(name = "movie-service", configuration = LoadBalancerConfiguration.class)
public interface MovieFeignClient {

    @GetMapping("/movies/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable(value = "genre") String genre);

    @GetMapping("/movies/withErrors/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenreWithThrowError(@PathVariable(value = "genre") String genre,
                                                              @RequestParam("throwError") boolean throwError);
}
