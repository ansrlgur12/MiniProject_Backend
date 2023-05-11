package com.chicchic.mini_project.Repository;

import com.chicchic.mini_project.Entity.PerfumeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfumeDetailRepository extends JpaRepository<PerfumeDetail, Integer> {
}
