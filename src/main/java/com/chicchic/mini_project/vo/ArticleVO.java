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

    //댓글부분
    private int commentNum;
    private String commentText;
    private String commentPwd;
    private Date commentDate;

    //조회수
    private int view;

    //좋아요수
    private int like;

    // 회원

    private String userImg;
    private int userGrade;

    // 몇 초전
    private String date2;


    // 향수부분
    private String perfumeName;
    private String oneLineText;
    private int star;

    // 게시판
    private String Bname;
}
