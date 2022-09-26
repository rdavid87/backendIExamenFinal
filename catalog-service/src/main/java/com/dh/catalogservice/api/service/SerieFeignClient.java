package com.dh.catalogservice.api.service;

import com.dh.catalogservice.api.model.movie.Movie;
import com.dh.catalogservice.api.model.serie.Serie;
import com.dh.catalogservice.config.LoadBalancerConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "serie-service")
@LoadBalancerClient(name = "serie-service", configuration = LoadBalancerConfiguration.class)
public interface SerieFeignClient {

    @GetMapping("/series/{genre}")
    ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable(value = "genre") String genre);

    @GetMapping("/series/withErrors/{genre}")
    ResponseEntity<List<Serie>> getSerieByGenreWithThrowError(@PathVariable(value = "genre") String genre,
                                                              @RequestParam("throwError") boolean throwError);
}
