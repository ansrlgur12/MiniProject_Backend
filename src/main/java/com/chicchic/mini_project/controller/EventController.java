package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.dao.EventDAO;
import com.chicchic.mini_project.vo.EventVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Date;
//import java.sql.Date;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EventController {
    @GetMapping("/EventPage/{eventNum}/{view}")
    public ResponseEntity<List<EventVO>> eventList(@PathVariable("eventNum") int eventNum, @PathVariable("view") int view){
        EventDAO dao = new EventDAO();
        List<EventVO> list = dao.eventList(eventNum, view);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/EventDesc/{eventNum}")
    public ResponseEntity<List<EventVO>> eventDesc(@PathVariable("eventNum") int eventNum){
        EventDAO dao = new EventDAO();
        List<EventVO> list = dao.eventDesc(eventNum);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // 이벤트 등록
    @PostMapping("/newEvent")
    public ResponseEntity<Boolean> newEvent(@RequestBody Map<String, String> regData) {
        int getEventNum = Integer.parseInt(regData.get("eventNum"));
        String getEventTitle = regData.get("eventTitle");
        String getEventText = regData.get("eventText");
        String getEventImg = regData.get("eventImg");

//        String startEventStr = regData.get("startEvent");
//        String endEventStr = regData.get("endEvent");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date getStartEvent = null;
        Date getEndEvent = null;

        try {
            getStartEvent = dateFormat.parse(regData.get("startEvent"));
            getEndEvent = dateFormat.parse(regData.get("endEvent"));
        } catch (ParseException e) {
            // 날짜 형식 파싱 오류 처리
            e.printStackTrace();
        }
        EventDAO dao = new EventDAO();
        boolean isTrue = dao.newEvent(getEventNum, getEventTitle, getEventText, getEventImg, (java.sql.Date) getStartEvent, (java.sql.Date) getEndEvent);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }
}

