package com.chicchic.mini_project.vo;

public class PerfumeReviewVO {
    private int perfumeNumber;
    private String userId;
    private int starRating;
    private String review;

    public PerfumeReviewVO(int perfumeNum, String userId, int starRating, String review) {
        this.perfumeNumber = perfumeNum;
        this.userId = userId;
        this.starRating = starRating;
        this.review = review;

    }


    @Override
    public String toString() {
        return "PerfumeReviewVO{" +
                "perfumeNumber=" + perfumeNumber +
                ", userId='" + userId + '\'' +
                ", starRating=" + starRating +
                ", review='" + review + '\'' +
                '}';
    }

// getters and setters


    public int getPerfumeNumber() {
        return perfumeNumber;
    }

    public void setPerfumeNumber(int perfumeNumber) {
        this.perfumeNumber = perfumeNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
