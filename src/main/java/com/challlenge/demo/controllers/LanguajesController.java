package com.challlenge.demo.controllers;


import com.challlenge.demo.repositories.LanguajeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/languajes")
public class LanguajesController {

    private final LanguajeRepository languajeRepository;

    public LanguajesController(LanguajeRepository languajeRepository) {
        this.languajeRepository = languajeRepository;
    }

    @GetMapping(path = "/")
    public ResponseEntity getLanguajes(){
        return ResponseEntity.status(200).body(languajeRepository.findAll());
    }

}
