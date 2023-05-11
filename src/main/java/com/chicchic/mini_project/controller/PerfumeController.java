package com.chicchic.mini_project.controller;// PerfumeController.java

import com.chicchic.mini_project.dao.PerfumeDAO;
import com.chicchic.mini_project.vo.PerfumeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerfumeController {

    @Autowired
    private PerfumeDAO perfumeDAO;

    @GetMapping("/api/perfumes")
    public Page<PerfumeVO> getAllPerfumes(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return perfumeDAO.getAllPerfumes(pageable);
    }
}
