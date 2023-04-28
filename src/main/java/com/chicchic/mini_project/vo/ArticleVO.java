package com.chicchic.mini_project.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

public class ArticleVO {
    private int bnum;
    private int anum;
    private int pnum;
    private int unum;
    private String title;
    private String text;
    private String pwd;
    private Date date;
    private String img;
    private String id;

}
