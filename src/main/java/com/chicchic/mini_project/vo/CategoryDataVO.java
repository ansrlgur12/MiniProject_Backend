package com.chicchic.mini_project.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDataVO {
    private long pk;
    private String model;
    private CategoryVO fields;
    private String name;
}
