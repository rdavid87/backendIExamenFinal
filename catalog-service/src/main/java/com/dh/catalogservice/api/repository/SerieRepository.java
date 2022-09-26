package com.dh.catalogservice.api.repository;

import com.dh.catalogservice.api.model.serie.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SerieRepository extends MongoRepository<Serie, String> {

    List<Serie> findByGenre(String genre);
}
