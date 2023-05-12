package com.chicchic.mini_project.Repository;

import com.chicchic.mini_project.Entity.Brand;
import com.chicchic.mini_project.Entity.PerfumeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SearchRepository extends JpaRepository<PerfumeDetail, Integer> {

    List<PerfumeDetail> findByBrandAndPriceBetweenAndGender(
            Brand brand, double minPrice, double maxPrice, int gender);

    @Query("SELECT p.brand.name, COUNT(p) FROM PerfumeDetail p GROUP BY p.brand.name ORDER BY COUNT(p) DESC")
    List<Object[]> findTopBrandsWithCounts();
}
