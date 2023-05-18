package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.dao.PerfumeReviewDAO;
import com.chicchic.mini_project.vo.PerfumeReviewVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class PerfumeReviewController {
    private final PerfumeReviewDAO perfumeReviewDAO;

    public PerfumeReviewController(PerfumeReviewDAO perfumeReviewDAO) {
        this.perfumeReviewDAO = perfumeReviewDAO;
    }

    @RestController
    public class ReviewController {

        @PostMapping("/reviews")
        public ResponseEntity<Boolean> newReview(@RequestBody Map<String, String> regData) {
            int getperfumeNumber = Integer.parseInt(regData.get("perfumeNumber"));
            String getuserId = regData.get("userId");
            int getstarRating = Integer.parseInt(regData.get("starRating"));
            String getreview = regData.get("review");
            boolean isTrue = perfumeReviewDAO.newReview(getperfumeNumber, getuserId, getstarRating, getreview);
            return new ResponseEntity<>(isTrue, HttpStatus.OK);
        }


        @GetMapping("/reviews/{perfumeNumber}")
        public List<PerfumeReviewVO> viewReview(@PathVariable int perfumeNumber) {
            return perfumeReviewDAO.viewReview(perfumeNumber);
        }
    }

}
