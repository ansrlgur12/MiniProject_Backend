package com.chicchic.mini_project.vo;

import java.sql.Date;

public class PerfumeDetailVO {
        private int perfumeNumber;
        private String name;
        private Date launchDate;
        private String thumbnail;
        private int gender;
        private double price;
        private String brandName;
        private String topNotes;
        private String heartNotes;
        private String baseNotes;
        private String seasons;
        private String availability;
        private String categories;



        // Getter and Setter methods
        public String getThumbnail() {
                return this.thumbnail;
        }

        public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
        }
        public String getTopNotes() {
                return this.topNotes;
        }

        public void setTopNotes(String topNotes) {
                this.topNotes = topNotes;
        }

        public String getHeartNotes() {
                return this.heartNotes;
        }

        public void setHeartNotes(String heartNotes) {
                this.heartNotes = heartNotes;
        }

        public String getBaseNotes() {
                return this.baseNotes;
        }

        public void setBaseNotes(String baseNotes) {
                this.baseNotes = baseNotes;
        }

        public String getSeasons() {
                return this.seasons;
        }

        public void setSeasons(String seasons) {
                this.seasons = seasons;
        }

        public String getAvailability() {
                return this.availability;
        }

        public void setAvailability(String availability) {
                this.availability = availability;
        }

        public String getCategories() {
                return this.categories;
        }

        public void setCategories(String categories) {
                this.categories = categories;
        }

        public int getPerfumeNumber() {
                return perfumeNumber;
        }

        public void setPerfumeNumber(int perfumeNumber) {
                this.perfumeNumber = perfumeNumber;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Date getLaunchDate() {
                return launchDate;
        }

        public void setLaunchDate(Date launchDate) {
                this.launchDate = launchDate;
        }

        public int getGender() {
                return gender;
        }

        public void setGender(int gender) {
                this.gender = gender;
        }

        public double getPrice() {
                return price;
        }

        public void setPrice(double price) {
                this.price = price;
        }

        public String getBrandName() {
                return brandName;
        }

        public void setBrandName(String brandName) {
                this.brandName = brandName;
        }
}
