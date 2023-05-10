package com.chicchic.mini_project.Entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "PERFUMES")
public class PerfumeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int perfumeNumber;

    private String name;
    private Date launchDate;
    private int gender;
    private double price;


    @ManyToOne
    @JoinColumn(name = "BRAND")
    private Brand brand;

    // Getter and Setter methods

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

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    // ... ToString, equals, hashCode, etc.
}