package com.dh.movieservice.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "movies")
public class Movie implements Serializable {

    private static final long serialVersionUID = -6961756666589577603L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
