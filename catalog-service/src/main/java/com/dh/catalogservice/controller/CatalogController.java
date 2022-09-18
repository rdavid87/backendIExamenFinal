package com.dh.catalogservice.controller;

import com.dh.catalogservice.api.model.movie.Movie;
import com.dh.catalogservice.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final MovieService movieService;

    @Autowired
    public CatalogController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<Movie>> getGenre(@PathVariable String genre) {
        return movieService.findMovieByGenre(genre);
    }

}
