package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.dao.ImageTestDAO;
import com.chicchic.mini_project.vo.PerfumeVO;
import com.chicchic.mini_project.vo.PerfumesVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class ImageTestController {

@GetMapping("/ImageTestResult/{selected}")
public ResponseEntity<PerfumesVO> getPerfumesByConditions(@PathVariable("selected") Integer[] selected) {
    ImageTestDAO dao = new ImageTestDAO();
    PerfumesVO perfumes = dao.getPerfumesByConditions(selected);
    return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }
}