package com.chicchic.mini_project.Entity;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "PERFUMES")
public class PerfumeDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int perfumeNumber;

    private String name;
    private Date launchDate;
    private int gender;
    private String thumbnail;
    private String topNotes;
    private String heartNotes;
    private String baseNotes;
    private String seasons;
    private String availability;
    private String categories;


    private double price;


    @ManyToOne
    @JoinColumn(name = "BRAND")
    private Brand brand;

    // Getter and Setter methods
    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTopNotes() {
        return this.topNotes;
    }

    public void setTopNotes(String topNotes) {
        this.topNotes = topNotes;
    }

    public String getHeartNotes() {
        return this.heartNotes;
    }

    public void setHeartNotes(String heartNotes) {
        this.heartNotes = heartNotes;
    }

    public String getBaseNotes() {
        return this.baseNotes;
    }

    public void setBaseNotes(String baseNotes) {
        this.baseNotes = baseNotes;
    }

    public String getSeasons() {
        return this.seasons;
    }

    public void setSeasons(String seasons) {
        this.seasons = seasons;
    }

    public String getAvailability() {
        return this.availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getCategories() {
        return this.categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

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