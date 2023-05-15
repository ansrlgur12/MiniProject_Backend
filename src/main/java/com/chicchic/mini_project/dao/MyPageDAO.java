package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.ArticleVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.chicchic.mini_project.common.Common.getConnection;

public class MyPageDAO {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public List<ArticleVO> getImage(String id) {
        List<ArticleVO> list = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기

            String sql = "SELECT 이미지 " +
                    "FROM 회원" +
                    " WHERE 아이디 = '" + id + "'";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String userImg  = rs.getString("이미지");
                ArticleVO vo = new ArticleVO();
                vo.setUserImg(userImg);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
            System.out.println("프로필사진 불러오기 성공");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateImage(String id, String image) {
        int result = 0;
        String sql = "UPDATE 회원 SET 이미지 = ? WHERE 아이디 = ? ";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, image);
            pStmt.setString(2, id);

            result = pStmt.executeUpdate();
            System.out.println("회원 이미지 수정 DB 결과 확인 : " + result);
            System.out.println(image);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }
}
