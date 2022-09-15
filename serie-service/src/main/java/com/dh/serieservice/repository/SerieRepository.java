package com.dh.serieservice.repository;

import com.dh.serieservice.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SerieRepository extends MongoRepository<Serie, String> {
    List<Serie> findByGenre(String genre);
}

