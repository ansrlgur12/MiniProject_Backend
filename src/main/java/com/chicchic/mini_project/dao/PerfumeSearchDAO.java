package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.Entity.Brand;
import com.chicchic.mini_project.Entity.PerfumeDetail;
import com.chicchic.mini_project.Repository.BrandRepository;
import com.chicchic.mini_project.Repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PerfumeSearchDAO {

    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private BrandRepository brandRepository;

    public List<PerfumeDetail> searchPerfumes(String brandName, double minPrice, double maxPrice, int gender) {
        Brand brand = brandRepository.findByName(brandName).orElse(null);
        return searchRepository.findByBrandAndPriceBetweenAndGender(brand, minPrice, maxPrice, gender);
    }

    public Optional<Brand> findBrandByName(String brandName) {
        return brandRepository.findByName(brandName);
    }

    public List<Brand> getTopBrands(int limit) {
        List<Object[]> topBrandsWithCounts = searchRepository.findTopBrandsWithCounts();
        List<Brand> topBrands = new ArrayList<>();
        for (int i = 0; i < Math.min(limit, topBrandsWithCounts.size()); i++) {
            String brandName = (String) topBrandsWithCounts.get(i)[0];
            Brand brand = brandRepository.findByName(brandName).orElse(null);
            if (brand != null) {
                topBrands.add(brand);
            }
        }
        return topBrands;
    }


}
