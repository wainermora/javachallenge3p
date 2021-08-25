package com.challlenge.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "actors")
@Entity
public class Actor extends BaseEntity implements Serializable {
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String code;
}
