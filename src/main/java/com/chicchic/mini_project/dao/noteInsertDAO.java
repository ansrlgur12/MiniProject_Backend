package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;

import com.chicchic.mini_project.vo.noteDataVO;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class noteInsertDAO {
    private Connection conn = null;

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;
    private List<noteDataVO> noteList;


    public void noteInsert() {
        File file = new File("src/main/data/note.json");
        ObjectMapper mapper = new ObjectMapper();

        try {
            noteList = mapper.readValue(file, new TypeReference<List<noteDataVO>>() {
            });
            conn = Common.getConnection();


            String insertSql = "INSERT INTO notes(note_number, kor_name) VALUES (note_seq.nextval, ?)";
            pStmt = conn.prepareStatement(insertSql);

            for (noteDataVO note : noteList) {



                if ( note.getFields() == null) {
                    continue;
                }
                pStmt.setString(1, note.getFields().getKor_name());



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
