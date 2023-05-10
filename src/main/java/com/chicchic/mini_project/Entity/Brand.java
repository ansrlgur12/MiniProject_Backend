package com.chicchic.mini_project.Entity;

import javax.persistence.*;

@Entity
@Table(name = "BRAND")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandNumber;

    private String name;
    private String logoImage;

    // Getter and Setter methods
    public int getBrandNumber() {
        return brandNumber;
    }

    public void setBrandNumber(int brandNumber) {
        this.brandNumber = brandNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    // ... ToString, equals, hashCode, etc.
}
