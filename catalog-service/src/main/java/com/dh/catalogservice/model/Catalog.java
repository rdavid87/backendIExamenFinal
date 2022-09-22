package com.dh.catalogservice.model;

import com.dh.catalogservice.api.model.movie.Movie;
import com.dh.catalogservice.api.model.serie.Serie;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "catalogs")
public class Catalog {
    @Id
    private String genre;

    private List<Serie> series;

    private List<Movie> movies;

    public Catalog() {
        // Non-arguments
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "genre='" + genre + '\'' +
                ", series=" + series +
                ", movies=" + movies +
                '}';
    }
}
