package com.chicchic.mini_project.vo;


import java.sql.Date;
import java.util.List;

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
    public void setThumbnail(String thumbnail) {this.thumbnail = thumbnail;}
    private Long perfume_number;
    private Date launch_date;
    private Integer gender;
    private String availability;
    private Integer brand;
    private Double price;
    private List<String> top_notes;
    private List<String> heart_notes;
    private List<String> base_notes;
    private List<String> seasons;
    private List<String> categories;
}
