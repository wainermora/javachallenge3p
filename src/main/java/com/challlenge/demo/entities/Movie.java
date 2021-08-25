package com.challlenge.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie   extends BaseEntity implements Serializable {
    private String title;
    private int year;
    private double rated;
    private Calendar released;
    private int runtime;
    private Genre genre;
    private Director director;
    private Collection<Actor> actor;
    private String plot;
    private Collection<Languaje> languajes;
    private double imdbRating;
    private String type;

}
