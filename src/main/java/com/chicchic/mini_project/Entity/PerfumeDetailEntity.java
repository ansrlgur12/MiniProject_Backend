package com.chicchic.mini_project.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Date;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "PERFUMES")
public class PerfumeDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int perfumeNumber;

    private String name;
    private Date launchDate;
    private Integer gender;
    private String thumbnail;


    private String availability;



    private double price;


    @ManyToOne
    @JoinColumn(name = "BRAND")
    private Brand brand;
    @OneToMany(mappedBy = "perfume")
    @JsonManagedReference
    private List<TopNote> topNote;

    @OneToMany(mappedBy = "perfume")
    @JsonManagedReference
    private List<MiddleNote> middleNote;

    @OneToMany(mappedBy = "perfume")
    @JsonManagedReference
    private List<BaseNote> baseNote;

    @OneToMany(mappedBy = "perfume")
    @JsonManagedReference
    private  List<PerfumeSesaons> seasons;
    // Getter and Setter methods

    // ... ToString, equals, hashCode, etc.
}