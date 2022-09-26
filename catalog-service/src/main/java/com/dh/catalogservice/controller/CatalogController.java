package com.dh.catalogservice.controller;

import com.dh.catalogservice.api.model.movie.Movie;
import com.dh.catalogservice.api.model.serie.Serie;
import com.dh.catalogservice.api.service.MovieService;
import com.dh.catalogservice.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final MovieService movieService;
    private final CatalogService catalogService;

    @Autowired
    public CatalogController(MovieService movieService, CatalogService catalogService){
        this.movieService = movieService;
        this.catalogService = catalogService;
    }

    @GetMapping("/movies/{genre}")
    public ResponseEntity<List<Movie>> getMoviesByGenre(@PathVariable String genre) {
        return movieService.findMovieByGenre(genre);
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<Movie>> getCatalogByGenre(@PathVariable String genre){
        return ResponseEntity.ok().body(catalogService.findCatalogByGenre(genre));
    }

    @PostMapping("/movies")
    public ResponseEntity<String> saveMovieQueue(@RequestBody Movie movie){
        catalogService.saveMovie(movie);
        return ResponseEntity.ok("La movie se envió a la queue");
    }

    @PostMapping("/series")
    public ResponseEntity<String> saveSerieQueue(@RequestBody Serie serie){
        catalogService.saveSerie(serie);
        return ResponseEntity.ok("La serie se envió a la queue");
    }
}
