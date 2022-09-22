package com.dh.catalogservice.api.repository;

import com.dh.catalogservice.api.model.serie.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SerieRepository extends MongoRepository<Serie, String> {

}
