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
@Table(name = "genres")
@Entity
public class Genre extends BaseEntity implements Serializable {
    @Column
    private String name;
    @Column
    private String code;

}
