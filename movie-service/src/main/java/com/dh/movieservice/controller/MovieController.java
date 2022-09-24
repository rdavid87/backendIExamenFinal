package com.dh.movieservice.controller;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    //@Autowired
    //RabbitMQSender rabbitMQSender;
    @Autowired
    public MovieController(MovieService movieService){
        this.movieService=movieService;
    }

    @GetMapping
    public ResponseEntity<List<Movie>> movieList(){
        return ResponseEntity.ok().body(movieService.movieList());
    }

    @PostMapping
    public ResponseEntity<Movie> movieSave(@RequestBody Movie movie){
       //rabbitMQSender.send(movie.toString());
        return ResponseEntity.ok().body(movieService.movieSave(movie));
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<Movie>> movieListByGenre(@PathVariable String genre){
        return ResponseEntity.ok().body(movieService.movieListByGenre(genre));
    }
}
