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
import java.util.Collections;
import java.util.Objects;
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

        if (optionalPerfumeDetail.isPresent() ) {
            PerfumeDetailEntity perfumeDetail = optionalPerfumeDetail.get();
            PerfumeDetailVO perfumeDetailVO = new PerfumeDetailVO();
            perfumeDetailVO.setPerfumeNumber(Optional.ofNullable(perfumeDetail.getPerfumeNumber()).orElse(0));
            perfumeDetailVO.setName(Optional.ofNullable(perfumeDetail.getName()).orElse(null));
            perfumeDetailVO.setLaunchDate(Optional.ofNullable(perfumeDetail.getLaunchDate()).map(Date::getTime).map(Date::new).orElse(null));
            perfumeDetailVO.setGender(Optional.ofNullable(perfumeDetail.getGender()).orElse(null));
            perfumeDetailVO.setThumbnail(Optional.ofNullable(perfumeDetail.getThumbnail()).orElse(null));
            perfumeDetailVO.setPrice(Optional.ofNullable(perfumeDetail.getPrice()).orElse(0.0));
            perfumeDetailVO.setBrandName(Optional.ofNullable(perfumeDetail.getBrand()).map(Brand::getName).orElse(null));

            perfumeDetailVO.setTopNote(Optional.ofNullable(perfumeDetail.getTopNote()).orElse(Collections.emptyList()).stream().filter(Objects::nonNull).map(TopNote::getNote).filter(Objects::nonNull).map(com.chicchic.mini_project.Entity.Note::getKor_name).collect(Collectors.toList()));
            perfumeDetailVO.setMiddleNote(Optional.ofNullable(perfumeDetail.getMiddleNote()).orElse(Collections.emptyList()).stream().filter(Objects::nonNull).map(MiddleNote::getNote).filter(Objects::nonNull).map(com.chicchic.mini_project.Entity.Note::getKor_name).collect(Collectors.toList()));
            perfumeDetailVO.setBaseNote(Optional.ofNullable(perfumeDetail.getBaseNote()).orElse(Collections.emptyList()).stream().filter(Objects::nonNull).map(BaseNote::getNote).filter(Objects::nonNull).map(com.chicchic.mini_project.Entity.Note::getKor_name).collect(Collectors.toList()));
            perfumeDetailVO.setSeasons(Optional.ofNullable(perfumeDetail.getSeasons()).orElse(Collections.emptyList()).stream().filter(Objects::nonNull).map(PerfumeSesaons::getSeason).filter(Objects::nonNull).map(Season::getName).collect(Collectors.toList()));
            return ResponseEntity.ok(perfumeDetailVO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
