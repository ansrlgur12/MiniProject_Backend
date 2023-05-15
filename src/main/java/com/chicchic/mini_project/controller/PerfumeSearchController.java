package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.Entity.PerfumeDetailEntity;
import com.chicchic.mini_project.Entity.Brand;
import com.chicchic.mini_project.dao.PerfumeDAO;
import com.chicchic.mini_project.dao.PerfumeSearchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/perfumes")
public class PerfumeSearchController {

    @Autowired
    private PerfumeDAO perfumeDAO;

    @Autowired
    private PerfumeSearchDAO perfumeSearchDAO;

    @GetMapping("/search")
    public List<PerfumeDetailEntity> searchPerfumes(
            @RequestParam(value = "brandIdentifier", required = false) String brandIdentifier,
            @RequestParam(value = "minPrice", defaultValue = "0") double minPrice,
            @RequestParam(value = "maxPr ice", defaultValue = "10000") double maxPrice,
            @RequestParam(value = "gender", defaultValue = "0") int gender) {
        return perfumeSearchDAO.searchPerfumes(brandIdentifier, minPrice, maxPrice, gender);
    }

    @GetMapping("/top-brands")
    public List<Brand> getTopBrands(@RequestParam(value = "limit", defaultValue = "10") int limit) {
        return perfumeSearchDAO.getTopBrands(limit);
    }
}
