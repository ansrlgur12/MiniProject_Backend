
package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.Entity.Brand;
import com.chicchic.mini_project.Entity.PerfumeDetailEntity;
import com.chicchic.mini_project.Repository.BrandRepository;
import com.chicchic.mini_project.Repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class PerfumeSearchDAO {

    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private BrandRepository brandRepository;

    public List<PerfumeDetailEntity> searchPerfumes(String brandName, double minPrice, double maxPrice, int gender) {
        // 브랜드 이름이 제공되었을 때만 브랜드를 검색
        Brand brand = brandName.isEmpty() ? null : brandRepository.findByName(brandName).orElse(null);

        // Specification을 이용해 동적 쿼리 생성
        Specification<PerfumeDetailEntity> spec = Specification.where(null);

        if (brand != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("brand"), brand));
        }
        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("price"), minPrice, maxPrice));
        spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("gender"), gender));

        return searchRepository.findAll(spec);
    }

    public List<Brand> findBrandsByNumbers() {
        List<Integer> brandNumbers = Arrays.asList(528, 555, 614, 653, 1054, 1525, 1934, 2104, 3032, 3140);
        return searchRepository.findBrandsByNumbers(brandNumbers);
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

    public List<PerfumeDetailEntity> searchPerfumesByName(String perfumeName) {
        return searchRepository.findByNameContainingIgnoreCase(perfumeName.toLowerCase());
    }



}