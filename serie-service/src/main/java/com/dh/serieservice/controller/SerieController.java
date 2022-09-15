package com.dh.serieservice.controller;

import com.dh.serieservice.model.Serie;
import com.dh.serieservice.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok().body(service.serieListAll());
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<Serie>> serieListByGenre(@PathVariable String genre){
        return ResponseEntity.ok().body(service.serieListByGenre(genre));
    }

    @PostMapping
    public ResponseEntity<Serie> serieSave(@RequestBody Serie serie){
        return ResponseEntity.ok().body(service.serieSave(serie));
    }
}
