package com.challlenge.demo.controllers;

import com.challlenge.demo.entities.Movie;
import com.challlenge.demo.repositories.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    protected MovieController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @GetMapping(path = "/")
    public ResponseEntity getMovies(){
        return ResponseEntity.status(200).body(movieRepository.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getMovie(@PathVariable Long id){
        Movie movie = movieRepository.findById(id).get();
        return ResponseEntity.status(200).body(movie);
    }

    @PostMapping(path = "/",consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity addMovie(@RequestBody Movie unsaved){
        Movie saved = movieRepository.save(unsaved);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}
