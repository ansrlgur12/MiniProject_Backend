package com.chicchic.mini_project.vo;

import java.sql.Date;

public class PerfumeDetailVO {
        private int perfumeNumber;
        private String name;
        private Date launchDate;
        private int gender;
        private double price;
        private String brandName;



        // Getter and Setter methods
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
