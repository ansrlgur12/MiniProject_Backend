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

    public int getPerfumeNumber() {
        return perfumeNumber;
    }

    public void setPerfumeNumber(int perfumeNumber) {
        this.perfumeNumber = perfumeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
