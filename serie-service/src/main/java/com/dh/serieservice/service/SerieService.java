package com.dh.serieservice.service;

import com.dh.serieservice.model.Serie;
import com.dh.serieservice.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SerieService {

    private final SerieRepository repository;

    @Autowired
    public SerieService(SerieRepository serieRepository){
        this.repository = serieRepository;
    }

    public List<Serie> serieListAll(){
        return repository.findAll();
    }

    public List<Serie> serieListByGenre(String genre){
        return repository.findByGenre(genre);
    }

    public Serie serieSave(Serie serie){
        return repository.save(serie);
    }

}
