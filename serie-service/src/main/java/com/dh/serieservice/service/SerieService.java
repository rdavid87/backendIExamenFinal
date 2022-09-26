package com.dh.serieservice.service;

import com.dh.serieservice.model.Serie;
import com.dh.serieservice.repository.SerieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SerieService {

    private static final Logger LOG = LoggerFactory.getLogger(SerieService.class);
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

    @RabbitListener(queues = "${queue.serie.name}")
    public void serieSaveQueue(Serie serie){
        LOG.info("Se recibio una serie a través de rabbit " + serie.toString());
        serieSave(serie);
    }
    public Serie serieSave(Serie serie){
        return repository.save(serie);
    }

}
