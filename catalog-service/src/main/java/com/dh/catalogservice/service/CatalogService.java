package com.dh.catalogservice.service;

import com.dh.catalogservice.api.model.movie.Movie;
import com.dh.catalogservice.api.model.serie.Serie;
import com.dh.catalogservice.api.service.MovieService;
import com.dh.catalogservice.api.service.SerieService;
import com.dh.catalogservice.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    private final MovieService movieServiceApi;
    private final SerieService serieServiceApi;
    private final CatalogRepository catalogRepository;

    @Autowired
    public CatalogService(MovieService movieService, SerieService serieService, CatalogRepository catalogRepository){
        this.movieServiceApi = movieService;
        this.serieServiceApi = serieService;
        this.catalogRepository = catalogRepository;
    }

    public List<Movie> findCatalogByGenre(String genre) {
        List<Movie> moviesByGenre = movieServiceApi.findMovieByGenre(genre).getBody();
        movieServiceApi.movieSaveAll(moviesByGenre);

        List<Serie> seriesByGenre = serieServiceApi.findSerieByGenre(genre).getBody();
        serieServiceApi.serieSaveAll(seriesByGenre);
        return moviesByGenre;
    }







}
