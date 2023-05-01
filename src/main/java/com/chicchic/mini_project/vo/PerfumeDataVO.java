package com.chicchic.mini_project.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;
@Getter
@Setter
@ToString
public class PerfumeDataVO {
    private Long pk;
    private String model;
    private PerfumesVO fields;

    private Long perfume_number;
    private String name;
    private String launch_date;
    private String thumbnail;
    private Integer gender;
    private String availability;
    private Integer brand;
    private Integer price;
    private List<Integer> top_notes;
    private List<Integer> heart_notes;
    private List<Integer> base_notes;
    private List<String> seasons;
    private List<String> categories;
}


