package com.chicchic.mini_project.vo;

public class PerfumeSearchVO {
    private Integer brand;
    private Double minPrice;
    private Double maxPrice;
    private Integer gender;

    // getter, setter 및 기타 메소드를 여기에 추가하세요


    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
