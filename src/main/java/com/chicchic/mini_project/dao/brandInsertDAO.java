package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.PerfumeDataVO;
import com.chicchic.mini_project.vo.brandDataVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class brandInsertDAO {

        private Connection conn = null;

        private ResultSet rs = null;
        private PreparedStatement pStmt = null;
        private List<brandDataVO> brandList;


        public void brandInsert() {
            File file = new File("src/main/data/brand.json");
            ObjectMapper mapper = new ObjectMapper();

            try {
                brandList = mapper.readValue(file, new TypeReference<List<brandDataVO>>() {
                });
                conn = Common.getConnection();


                String insertSql = "INSERT INTO brand(brand_number, name, logo_image) VALUES (brand_seq.nextval,?, ?)";
                pStmt = conn.prepareStatement(insertSql);

                for (brandDataVO brand : brandList) {



                    if ( brand.getFields() == null) {
                        continue;
                    }
                    pStmt.setString(1, brand.getFields().getName());
                    pStmt.setString(2, brand.getFields().getLogo_image());


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

