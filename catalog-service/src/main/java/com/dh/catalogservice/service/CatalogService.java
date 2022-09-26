package com.dh.catalogservice.service;

import com.dh.catalogservice.api.model.movie.Movie;
import com.dh.catalogservice.api.model.serie.Serie;
import com.dh.catalogservice.api.repository.MovieRepository;
import com.dh.catalogservice.api.repository.SerieRepository;
import com.dh.catalogservice.api.service.MovieService;
import com.dh.catalogservice.api.service.SerieService;
import com.dh.catalogservice.model.Catalog;
import com.dh.catalogservice.repository.CatalogRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    private final SerieRepository serieRepository;
    private final MovieRepository movieRepository;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CatalogService(MovieService movieService, SerieService serieService, CatalogRepository catalogRepository, RabbitTemplate rabbitTemplate, MovieRepository movieRepository, SerieRepository serieRepository){
        this.movieServiceApi = movieService;
        this.serieServiceApi = serieService;
        this.catalogRepository = catalogRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.movieRepository = movieRepository;
        this.serieRepository = serieRepository;
    }

    public List<Catalog> findCatalogByGenre(String genre) {
        List<Movie> moviesByGenre = findCatalogByGenreMovieApi(genre);
        movieServiceApi.movieSaveAll(moviesByGenre);

        List<Serie> seriesByGenre = findCatalogByGenreSerieApi(genre);
        serieServiceApi.serieSaveAll(seriesByGenre);
        return catalogRepository.findByGenre(genre);
    }

    //CIRCUIT BREAK se activa en caso de que haya un error en la comunicación con movie-service
    //haciendo un llamado a la información local de movie findCatalogByGenreMovie
    @CircuitBreaker(name = "movies", fallbackMethod = "findCatalogByGenreMovie")
    public List<Movie> findCatalogByGenreMovieApi(String genre){
        List<Movie> moviesByGenre = movieServiceApi.findMovieByGenre(genre).getBody();
        return moviesByGenre;
    }
    public List<Movie> findCatalogByGenreMovie(String genre){
        List<Movie> moviesByGenre = movieRepository.findByGenre(genre);
        return moviesByGenre;
    }

    //CIRCUIT BREAK se activa en caso de que haya un error en la comunicación con serie-service
    //haciendo un llamado a la información local de serie findByCatalogByGenreSerie
    @CircuitBreaker(name = "series", fallbackMethod = "findByCatalogByGenreSerie")
    public List<Serie> findCatalogByGenreSerieApi(String genre){
        List<Serie> seriesByGenre = serieServiceApi.findSerieByGenre(genre).getBody();
        return  seriesByGenre;
    }

    public List<Serie> findByCatalogByGenreSerie(String genre){
        List<Serie> seriesByGenre = serieRepository.findByGenre(genre);
        return seriesByGenre;
    }

    public void saveMovie(Movie movie) {
        //usa la cola para guardar la movie que es recibida por el microservicio movie-service
        rabbitTemplate.convertAndSend(movieQueue, movie);
    }

    public void saveSerie(Serie serie) {
        //usa la cola para guardar la movie que es recibida por el microservicio movie-service
        rabbitTemplate.convertAndSend(serieQueue, serie);
    }






}
