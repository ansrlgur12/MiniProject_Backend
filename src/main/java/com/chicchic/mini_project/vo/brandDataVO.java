package com.chicchic.mini_project.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class brandDataVO {
    private long pk;
    private String model;
    private brandVO fields;
    private String name;
    private String logo_image;
}
