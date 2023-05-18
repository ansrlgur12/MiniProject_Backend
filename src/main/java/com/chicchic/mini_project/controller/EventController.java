package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.dao.EventDAO;
import com.chicchic.mini_project.vo.EventVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}

