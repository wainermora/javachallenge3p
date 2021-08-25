package com.challlenge.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
@Entity
public class Movie   extends BaseEntity implements Serializable {
    @Column
    private String title;

    @Column
    private int year;

    @Column
    private double rated;

    @Column
    private Calendar released;

    @Column
    private int runtime;

    @ManyToOne
    @JoinColumn(name="genre")
    private Genre genre;


    @ManyToOne
    @JoinColumn(name="director")
    private Director director;


    @ManyToMany
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors;

    @Column
    private String plot;

    @ManyToMany
    @JoinTable(
            name = "movie_languajes",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "languaje_id"))
    private Set<Languaje> languajes;

    @Column
    private double imdbRating;

    @Column
    private String type;

}