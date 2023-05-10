package com.chicchic.mini_project.Entity;

import javax.persistence.*;

@Entity
@Table(name = "perfumes")
public class Perfume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int perfumeNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "thumbnail")
    private String thumbnail;

    // Getters and setters 생략
}
