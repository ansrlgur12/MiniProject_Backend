package com.chicchic.mini_project.vo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

public class EventVO {
    private int eventNum;
    private String eventTitle;
    private String eventText;
    private String eventImg;
    private Date startEvent;
    private Date endEvent;
}
