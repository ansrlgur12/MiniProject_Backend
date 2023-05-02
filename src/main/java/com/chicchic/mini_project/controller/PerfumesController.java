package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.dao.PerfumeDAO;
import com.chicchic.mini_project.vo.PerfumeVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class PerfumesController {
    private PerfumeDAO dao;

    public PerfumesController() {
        dao = new PerfumeDAO();
    }

    @GetMapping("/api/perfumes")
    public List<PerfumeVO> getAllPerfumes() {
        return dao.getAllPerfumes();
    }

}
