package com.dh.catalogservice.api.service;

import com.dh.catalogservice.api.model.movie.Movie;
import com.dh.catalogservice.api.model.serie.Serie;
import com.dh.catalogservice.api.repository.SerieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    private final SerieFeignClient serieFeignClient;
    private final SerieRepository serieRepository;

    private final Logger LOG = LoggerFactory.getLogger(SerieService.class);

    @Autowired
    public SerieService(SerieFeignClient serieFeignClient, SerieRepository serieRepository){
        this.serieFeignClient = serieFeignClient;
        this.serieRepository = serieRepository;
    }

    public ResponseEntity<List<Serie>> findSerieByGenre(String genre) {
        LOG.info("Se va a incluir el llamado al serie-service...");
        return serieFeignClient.getSerieByGenre(genre);
    }

    public void serieSaveAll(List<Serie> serieList){
        serieRepository.saveAll(serieList);
    }

}
