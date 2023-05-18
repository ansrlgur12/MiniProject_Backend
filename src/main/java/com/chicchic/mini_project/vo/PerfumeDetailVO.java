package com.chicchic.mini_project.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter

public class PerfumeDetailVO {
        private int perfumeNumber;
        private String name;
        private Date launchDate;
        private String thumbnail;
        private Integer gender;
        private double price;
        private String brandName;
        private String topNotes;
        private String heartNotes;
        private String baseNotes;

        private String availability;
        private String categories;
        private List<String> topNote;
        private List<String> middleNote;
        private List<String> baseNote;
        private List<String> seasons;


        // Getter and Setter methods

}
