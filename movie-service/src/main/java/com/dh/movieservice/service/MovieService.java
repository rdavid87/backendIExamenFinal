package com.dh.movieservice.service;

import com.dh.movieservice.repository.MovieRepository;
import com.dh.movieservice.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);
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

    @RabbitListener(queues = "${queue.movie.name}")
    public void movieSaveQueue(Movie movie){
        LOG.info("Se recibio una movie a través de rabbit " + movie.toString());
        movieSave(movie);
    }

    public Movie movieSave(Movie movie){
        return movieRepository.save(movie);
    }
}
