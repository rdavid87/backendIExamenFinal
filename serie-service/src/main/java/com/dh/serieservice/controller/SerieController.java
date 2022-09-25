package com.dh.serieservice.controller;

import com.dh.serieservice.model.Serie;
import com.dh.serieservice.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/series")
public class SerieController {

    private final SerieService service;

    @Autowired
    public SerieController(SerieService serieService){
        this.service = serieService;
    }

    @GetMapping
    public ResponseEntity<List<Serie>> serieListAll(){
        List<Serie> serieList = service.serieListAll();
        return serieList.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(serieList);
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<Serie>> serieListByGenre(@PathVariable String genre){
        List<Serie> serieList = service.serieListByGenre(genre);
        return serieList.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(serieList);
    }

    @PostMapping
    public ResponseEntity<Serie> serieSave(@RequestBody Serie serie){
        return ResponseEntity.ok().body(service.serieSave(serie));
    }
}
