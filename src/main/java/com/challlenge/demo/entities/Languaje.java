package com.challlenge.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "languajes")
public class Languaje extends BaseEntity implements Serializable {
    private String name;
    private String code;
}
