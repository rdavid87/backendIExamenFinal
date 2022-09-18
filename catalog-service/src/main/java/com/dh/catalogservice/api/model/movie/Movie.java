package com.dh.catalogservice.api.model.movie;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collation = "movies")
public class Movie {

    @Id
    private Integer id;

    private String name;

    private String genre;

    private String ulrStream;

    public Movie() {
    }

    public Integer getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUlrStream() {
        return ulrStream;
    }

    public void setUlrStream(String ulrStream) {
        this.ulrStream = ulrStream;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", ulrStream='" + ulrStream + '\'' +
                '}';
    }
}
