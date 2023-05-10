package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.Entity.PerfumeDetail;
import com.chicchic.mini_project.Repository.PerfumeDetailRepository;
import com.chicchic.mini_project.vo.PerfumeDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/perfume-details")
public class PerfumeDetailController {

    @Autowired
    private PerfumeDetailRepository perfumeDetailRepository;

    @GetMapping("/{perfumeNumber}")
    public ResponseEntity<PerfumeDetailVO> getPerfumeDetail(@PathVariable int perfumeNumber) {
        Optional<PerfumeDetail> optionalPerfumeDetail = perfumeDetailRepository.findById(perfumeNumber);

        if (optionalPerfumeDetail.isPresent()) {
            PerfumeDetail perfumeDetail = optionalPerfumeDetail.get();
            PerfumeDetailVO perfumeDetailVO = new PerfumeDetailVO();
            perfumeDetailVO.setPerfumeNumber(perfumeDetail.getPerfumeNumber());
            perfumeDetailVO.setName(perfumeDetail.getName());
            perfumeDetailVO.setLaunchDate(new Date(perfumeDetail.getLaunchDate().getTime()));
            perfumeDetailVO.setGender(perfumeDetail.getGender());
            perfumeDetailVO.setPrice(perfumeDetail.getPrice());
            perfumeDetailVO.setBrandName(perfumeDetail.getBrand().getName());

            return ResponseEntity.ok(perfumeDetailVO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}