package com.dh.catalogservice.service;

import com.dh.catalogservice.api.model.movie.Movie;
import com.dh.catalogservice.api.model.serie.Serie;
import com.dh.catalogservice.api.service.MovieService;
import com.dh.catalogservice.api.service.SerieService;
import com.dh.catalogservice.repository.CatalogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    @Value("${queue.movie.name}")
    private String movieQueue;

    @Value("${queue.serie.name}")
    private String serieQueue;

    private final Logger LOG = LoggerFactory.getLogger(CatalogService.class);
    private final MovieService movieServiceApi;
    private final SerieService serieServiceApi;
    private final CatalogRepository catalogRepository;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CatalogService(MovieService movieService, SerieService serieService, CatalogRepository catalogRepository, RabbitTemplate rabbitTemplate){
        this.movieServiceApi = movieService;
        this.serieServiceApi = serieService;
        this.catalogRepository = catalogRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Movie> findCatalogByGenre(String genre) {
        List<Movie> moviesByGenre = findCatalogByGenreMovie(genre);
        movieServiceApi.movieSaveAll(moviesByGenre);

        List<Serie> seriesByGenre = findCatalogByGenreSerie(genre);
        serieServiceApi.serieSaveAll(seriesByGenre);
        return moviesByGenre;
    }

    public List<Movie> findCatalogByGenreMovie(String genre){
        List<Movie> moviesByGenre = movieServiceApi.findMovieByGenre(genre).getBody();
        return moviesByGenre;
    }

    public List<Serie> findCatalogByGenreSerie(String genre){
        List<Serie> seriesByGenre = serieServiceApi.findSerieByGenre(genre).getBody();
        return  seriesByGenre;
    }

    public void saveMovie(Movie movie) {
        //usa la cola para guardar la movie que es recibida por el microservicio movie-service
        rabbitTemplate.convertAndSend(movieQueue, movie);
    }







}
