package com.chicchic.mini_project.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class noteDataVO {
    private long pk;
    private String model;
    private noteVO fields;
    private String name;
    private String kor_name;
}
