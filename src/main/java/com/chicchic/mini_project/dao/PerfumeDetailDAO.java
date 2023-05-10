package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.Entity.Brand;
import com.chicchic.mini_project.Entity.PerfumeDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class PerfumeDetailDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<PerfumeDetail> findById(int perfumeNumber) {
        String query = "SELECT p.PERFUME_NUMBER, p.NAME, p.LAUNCH_DATE, p.GENDER, p.PRICE, b.NAME AS BRAND_NAME, b.BRAND_NUMBER FROM PERFUMES p JOIN BRAND b ON p.BRAND = b.BRAND_NUMBER WHERE p.PERFUME_NUMBER = ?";

        return jdbcTemplate.queryForObject(query, new Object[]{perfumeNumber}, new RowMapper<Optional<PerfumeDetail>>() {
            @Override
            public Optional<PerfumeDetail> mapRow(ResultSet rs, int rowNum) throws SQLException {
                PerfumeDetail perfumeDetail = new PerfumeDetail();
                perfumeDetail.setPerfumeNumber(rs.getInt("PERFUME_NUMBER"));
                perfumeDetail.setName(rs.getString("NAME"));
                perfumeDetail.setLaunchDate(rs.getDate("LAUNCH_DATE"));
                perfumeDetail.setGender(rs.getInt("GENDER"));
                perfumeDetail.setPrice(rs.getDouble("PRICE"));

                Brand brand = new Brand();
                brand.setName(rs.getString("BRAND_NAME"));
                brand.setBrandNumber(rs.getInt("BRAND_NUMBER"));
                perfumeDetail.setBrand(brand);

                return Optional.of(perfumeDetail);
            }
        });
    }
}