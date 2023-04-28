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
    @GetMapping("/EventPage/{eventNum}")
    public ResponseEntity<List<EventVO>> eventList(@PathVariable("eventNum") int eventNum){
        EventDAO dao = new EventDAO();
        List<EventVO> list = dao.eventDesc();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

