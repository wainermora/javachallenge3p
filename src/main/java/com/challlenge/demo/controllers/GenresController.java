package com.challlenge.demo.controllers;


import com.challlenge.demo.repositories.GenreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/genres")
public class GenresController {

    private final GenreRepository genreRepository;

    public GenresController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping(path = "/")
    public ResponseEntity getGenres(){
        return ResponseEntity.status(200).body(genreRepository.findAll());
    }

}

