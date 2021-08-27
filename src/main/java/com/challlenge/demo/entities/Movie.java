package com.challlenge.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
@Entity
@XmlRootElement
public class Movie   extends BaseEntity implements Serializable {
    @Column
    @NotNull
    @NotEmpty
    private String title;

    @Column
    private int year;

    @Column
    private String rated;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Calendar released;

    @Column
    private int runtime;

    @ManyToOne
    @JoinColumn(name="genre")
    private Genre genre;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="director")
    private Director director;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors;

    @Column
    @NotNull
    @NotEmpty
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
