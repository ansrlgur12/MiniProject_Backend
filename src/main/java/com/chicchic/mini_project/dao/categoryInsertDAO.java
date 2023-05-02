package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.CategoryDataVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class categoryInsertDAO {
    private Connection conn = null;

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;
    private List<CategoryDataVO> CategoryList;


    public void categoryInsert() {
        File file = new File("src/main/data/category.json");
        ObjectMapper mapper = new ObjectMapper();

        try {
            CategoryList = mapper.readValue(file, new TypeReference<List<CategoryDataVO>>() {
            });
            conn = Common.getConnection();


            String insertSql = "INSERT INTO category(category_number, name) VALUES (category_seq.nextval, ?)";
            pStmt = conn.prepareStatement(insertSql);

            for ( CategoryDataVO category : CategoryList) {



                if ( category.getFields() == null) {
                    continue;
                }
                pStmt.setString(1, category.getFields().getName());



                int r = pStmt.executeUpdate();
                System.out.println("SQL 실행: " + r + "개의 데이터 삽입");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(rs);
            Common.close(conn);
            Common.close(pStmt);
        }
    }
}
