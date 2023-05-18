package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.Entity.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "PERFUMES")
public class PerfumeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int perfumeNumber;

    private String name;
    private Date launchDate;
    private Integer gender;
    private String thumbnail;



    private double price;


    @ManyToOne
    @JoinColumn(name = "BRAND")
    private Brand brand;

    @OneToMany(mappedBy = "perfume")
    private List<TopNote> topNote;

    @OneToMany(mappedBy = "perfume")
    private List<MiddleNote> middleNote;

    @OneToMany(mappedBy = "perfume")
    private List<BaseNote> baseNote;

    @OneToMany(mappedBy = "perfume")
    private  List<PerfumeSesaons> seasons;
    // Getter and Setter methods

    // ... ToString, equals, hashCode, etc.
}