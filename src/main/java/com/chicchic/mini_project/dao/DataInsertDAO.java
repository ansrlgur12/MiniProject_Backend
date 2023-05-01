package com.chicchic.mini_project.dao;



import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.PerfumeDataVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.*;
import java.util.List;


public class DataInsertDAO {
    private Connection conn = null;

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;
    private List<PerfumeDataVO> perfumeList;


    public void PerfumeInsert() {
        File file = new File("src/main/data/perfumeData.json");
        ObjectMapper mapper = new ObjectMapper();

        try {
            perfumeList = mapper.readValue(file, new TypeReference<List<PerfumeDataVO>>() {
            });
            conn = Common.getConnection();


            String insertSql = "INSERT INTO perfumes(perfume_number, name, launch_date, thumbnail, gender, top_notes, heart_notes, base_notes, seasons, availability, brand, categories, price) VALUES (perfume_seq.nextval,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pStmt = conn.prepareStatement(insertSql);

            for (PerfumeDataVO perfume : perfumeList) {



                if ( perfume.getFields() == null) {
                    continue;
                }
                pStmt.setString(1, perfume.getFields().getName());
                pStmt.setString(2, perfume.getFields().getLaunch_date());
                pStmt.setString(3, perfume.getFields().getThumbnail());
                pStmt.setString(4, perfume.getFields().getGender());
                pStmt.setString(5, String.join(",", perfume.getFields().getTop_notes()));
                pStmt.setString(6, String.join(",", perfume.getFields().getHeart_notes()));
                pStmt.setString(7, String.join(",", perfume.getFields().getBase_notes()));
                pStmt.setString(8, String.join(",", perfume.getFields().getSeasons()));
                pStmt.setString(9, perfume.getFields().getAvailability());
                pStmt.setString(10, perfume.getFields().getBrand());
                pStmt.setString(11, String.join(",", perfume.getFields().getCategories()));
                pStmt.setString(12, perfume.getFields().getPrice());

                int r = pStmt.executeUpdate();
                System.out.println("SQL 실행: " + r + "개의 데이터 삽입");
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(rs);
            Common.close(conn);
            Common.close(pStmt);
        }
    }
}