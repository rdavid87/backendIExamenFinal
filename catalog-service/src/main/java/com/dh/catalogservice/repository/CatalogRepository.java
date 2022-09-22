package com.dh.catalogservice.repository;

import com.dh.catalogservice.model.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CatalogRepository extends MongoRepository<Catalog, String> {

    List<Catalog> findByGenre(String genre);
}
