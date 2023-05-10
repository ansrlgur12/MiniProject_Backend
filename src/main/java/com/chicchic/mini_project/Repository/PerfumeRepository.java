package com.chicchic.mini_project.Repository;

import com.chicchic.mini_project.Entity.Perfume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Integer> {
    Page<Perfume> findAll(Pageable pageable);
}
