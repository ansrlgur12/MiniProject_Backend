package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.PerfumeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository

public class PerfumeDAO {
    public Page<PerfumeVO> getAllPerfumes(Pageable pageable) {
        List<PerfumeVO> perfumes = new ArrayList<>();

        String countSql = "SELECT COUNT(*) FROM PERFUMES";
        String sql = "SELECT * FROM (SELECT t.*, ROWNUM rnum FROM (SELECT PERFUME_NUMBER, NAME, THUMBNAIL FROM PERFUMES ORDER BY PERFUME_NUMBER) t WHERE ROWNUM <= ?) WHERE rnum > ?";

        try (Connection connection = Common.getConnection();
             PreparedStatement countPreparedStatement = connection.prepareStatement(countSql);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Get the total count of perfumes
            ResultSet countResultSet = countPreparedStatement.executeQuery();
            countResultSet.next();
            long totalCount = countResultSet.getLong(1);

            // Set pagination parameters
            preparedStatement.setInt(1, (int) (pageable.getOffset() + pageable.getPageSize()));
            preparedStatement.setInt(2, (int) pageable.getOffset());


            // Get the perfume data
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PerfumeVO perfume = new PerfumeVO();
                perfume.setPerfumeNumber(resultSet.getInt("PERFUME_NUMBER"));
                perfume.setName(resultSet.getString("NAME"));
                perfume.setThumbnail(resultSet.getString("THUMBNAIL"));
                perfumes.add(perfume);
            }

            return new PageImpl<>(perfumes, pageable, totalCount);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new PageImpl<>(Collections.emptyList(), pageable, 0);
    }
}