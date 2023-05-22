package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.dao.PerfumeReviewDAO;
import com.chicchic.mini_project.vo.PerfumeReviewVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/reviews")
public class PerfumeReviewController {
    private final PerfumeReviewDAO perfumeReviewDAO;

    public PerfumeReviewController(PerfumeReviewDAO perfumeReviewDAO) {
        this.perfumeReviewDAO = perfumeReviewDAO;
    }

    @PostMapping
    public ResponseEntity<Boolean> newReview(@RequestBody Map<String, String> regData) {
        int getperfumeNumber = Integer.parseInt(regData.get("perfumeNumber"));
        String getuserId = regData.get("userId");
        int getstarRating = Integer.parseInt(regData.get("starRating"));
        String getreview = regData.get("review");
        boolean isTrue = perfumeReviewDAO.newReview(getperfumeNumber, getuserId, getstarRating, getreview);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    @GetMapping("/{perfumeNumber}")
    public List<PerfumeReviewVO> viewReview(@PathVariable int perfumeNumber) {
        return perfumeReviewDAO.viewReview(perfumeNumber);
    }

   /* @PutMapping("/{perfumeNumber}/{userId}")
    public ResponseEntity<Boolean> updateReview(@PathVariable int perfumeNumber, @PathVariable String userId, @RequestBody Map<String, String> updateData) {
        int starRating = Integer.parseInt(updateData.get("starRating"));
        String review = updateData.get("review");

        boolean isUpdated = perfumeReviewDAO.updateReview(perfumeNumber, userId, starRating, review);
        return new ResponseEntity<>(isUpdated, HttpStatus.OK);
    }*/

    @DeleteMapping("/{perfumeNumber}/{userId}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable int perfumeNumber, @PathVariable String userId) {
        boolean isDeleted = perfumeReviewDAO.deleteReview(perfumeNumber, userId);
        return new ResponseEntity<>(isDeleted, HttpStatus.OK);
    }
    @GetMapping("/viewReview/{perfumeNumber}/{userId}")
    public ResponseEntity<PerfumeReviewVO> viewReviewBeforeUpdate(@PathVariable int perfumeNumber, @PathVariable String userId) {
        PerfumeReviewVO review = perfumeReviewDAO.viewReviewBeforeUpdate(perfumeNumber, userId);
        if (review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
    }

}
