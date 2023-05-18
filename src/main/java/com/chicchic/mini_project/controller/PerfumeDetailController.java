package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.Entity.*;
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
import java.util.stream.Collectors;



@RestController
@RequestMapping("/perfume-details")
public class PerfumeDetailController {

    @Autowired
    private PerfumeDetailRepository perfumeDetailRepository;

    @GetMapping("/{perfumeNumber}")
    public ResponseEntity<PerfumeDetailVO> getPerfumeDetail(@PathVariable int perfumeNumber) {
        Optional<PerfumeDetailEntity> optionalPerfumeDetail = perfumeDetailRepository.findById(perfumeNumber);

        if (optionalPerfumeDetail.isPresent()) {
            PerfumeDetailEntity perfumeDetail = optionalPerfumeDetail.get();
            PerfumeDetailVO perfumeDetailVO = new PerfumeDetailVO();
            perfumeDetailVO.setPerfumeNumber(perfumeDetail.getPerfumeNumber());
            perfumeDetailVO.setName(perfumeDetail.getName());
            if (perfumeDetail.getLaunchDate() != null) {
                perfumeDetailVO.setLaunchDate(new Date(perfumeDetail.getLaunchDate().getTime()));
            }
            perfumeDetailVO.setGender(perfumeDetail.getGender());
            perfumeDetailVO.setThumbnail(perfumeDetail.getThumbnail());
            perfumeDetailVO.setPrice(perfumeDetail.getPrice());
            perfumeDetailVO.setBrandName(perfumeDetail.getBrand().getName());

            perfumeDetailVO.setTopNote(perfumeDetail.getTopNote().stream().map(TopNote::getNote).map(com.chicchic.mini_project.Entity.Note::getKor_name).collect(Collectors.toList()));
            perfumeDetailVO.setMiddleNote(perfumeDetail.getMiddleNote().stream().map(MiddleNote::getNote).map(com.chicchic.mini_project.Entity.Note::getKor_name).collect(Collectors.toList()));
            perfumeDetailVO.setBaseNote(perfumeDetail.getBaseNote().stream().map(BaseNote::getNote).map(com.chicchic.mini_project.Entity.Note::getKor_name).collect(Collectors.toList()));
            perfumeDetailVO.setSeasons(perfumeDetail.getSeasons().stream().map(PerfumeSesaons::getSeason).map(Season::getName).collect(Collectors.toList()));
            return ResponseEntity.ok(perfumeDetailVO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
