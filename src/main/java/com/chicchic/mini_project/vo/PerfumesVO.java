package com.chicchic.mini_project.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@ToString
@Getter
@Setter

public class PerfumesVO {
    private Long pk;
    private String model;
    private PerfumeDataVO fields;
    private Long perfumeNumber;
    private String name;
    private Date launch_date;
    private String thumbnail;
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
