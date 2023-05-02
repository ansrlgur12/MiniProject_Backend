package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.PerfumeVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerfumeDAO {
    public List<PerfumeVO> getAllPerfumes() {
        List<PerfumeVO> perfumes = new ArrayList<>();

        String sql = "SELECT * FROM PERFUMES";

        try (Connection connection = Common.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                PerfumeVO perfume = new PerfumeVO();
                perfume.setName(resultSet.getString("NAME"));
                perfumes.add(perfume);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return perfumes;
    }
}
