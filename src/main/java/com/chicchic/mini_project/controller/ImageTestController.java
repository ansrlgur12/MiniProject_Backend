package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.dao.ImageTestDAO;
import com.chicchic.mini_project.vo.PerfumesVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class ImageTestController {

@GetMapping("/ImageTestResult/{selected}")
public ResponseEntity<PerfumesVO> getPerfumesByConditions(@PathVariable("selected") Integer[] selected) {
    ImageTestDAO dao = new ImageTestDAO();
    PerfumesVO perfumes = dao.getPerfumesByConditions(selected);
    return new ResponseEntity<PerfumesVO>(perfumes, HttpStatus.OK);
    }
}