package com.chicchic.mini_project.vo;

public class PerfumeVO {
    private int perfumeNumber;
    private String name;

    private String thumbnail;

    public int getPerfumeNumber() {
        return perfumeNumber;
    }

    public void setPerfumeNumber(int perfumeNumber) {
        this.perfumeNumber = perfumeNumber;
    }


    public String getName() { // 추가
        return name;
    }

    public void setName(String name) { // 추가
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
