package com.dh.movieservice.service;

import com.dh.movieservice.repository.MovieRepository;
import com.dh.movieservice.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public List<Movie> movieList(){
        return  movieRepository.findAll();
    }

    public List<Movie> movieListByGenre(String genre){
        return movieRepository.findByGenre(genre);
    }

    public Movie movieSave(Movie movie){
        return movieRepository.save(movie);
    }
}
