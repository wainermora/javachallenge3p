package com.challlenge.demo.controllers;

import com.challlenge.demo.entities.Movie;
import com.challlenge.demo.exceptions.MovieNotFoundException;
import com.challlenge.demo.repositories.MovieRepository;
import com.challlenge.demo.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {

    private final MovieRepository movieRepository;
    private final NotificationService notificationService;

    protected MovieController(MovieRepository movieRepository,NotificationService notificationService){
        this.movieRepository = movieRepository;
        this.notificationService = notificationService;
    }

    @GetMapping(path = "/")
    public ResponseEntity getMovies(){
        return ResponseEntity.status(200).body(movieRepository.findAll());
    }

    @GetMapping(path = "/{id}",produces = { "application/json", "application/xml" } )
    public ResponseEntity getMovie(@PathVariable Long id){
        Movie movie = movieRepository.findById(id).orElseThrow(MovieNotFoundException::new);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    @PostMapping(path = "/",consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity addMovie(@Valid @RequestBody Movie unsaved){
        Movie saved = movieRepository.save(unsaved);
        notificationService.notifyFrontend(String.format("Movie %s created!", unsaved.getTitle()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping(path = "/",consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity updateMovie(@Valid @RequestBody Movie unsaved){
        Movie updated = movieRepository.save(unsaved);
        notificationService.notifyFrontend(String.format("Movie %s updated!", unsaved.getTitle()));
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

}
