package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.PerfumeReviewVO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PerfumeReviewDAO {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public boolean newReview(int perfumeNumber, String userId, int starRating, String review) { //평점 넣기 메서드
        int result = 0;

        String sql = "INSERT INTO 한줄평 VALUES ( ?, " +
                " (SELECT 회원번호 " +
                " FROM 회원 " +
                " WHERE 아이디 = ? ), ?, ? ) ";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, perfumeNumber);
            pStmt.setString(2, userId);
            pStmt.setInt(3, starRating);
            pStmt.setString(4, review);

            result = pStmt.executeUpdate();
            System.out.println("평점 등록 DB 결과 확인 : " + result);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pStmt != null) pStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (result == 1) return true;
        else return false;
    }

    public List<PerfumeReviewVO> viewReview(int perfumeNumber) {
        List<PerfumeReviewVO> list = new ArrayList<>();

        String sql = "SELECT 한줄평.*, 회원.아이디 FROM 한줄평 JOIN 회원 ON 한줄평.회원번호 = 회원.회원번호 WHERE 한줄평.향수번호= ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, perfumeNumber);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                int perfumeNum = rs.getInt("향수번호");
                String userId = rs.getString("아이디");
                int starRating = rs.getInt("별점");
                String review = rs.getString("한줄평");

                // 출력 전
                System.out.println("Before inserting into VO - PerfumeNum: " + perfumeNumber + ", UserId: " + userId + ", StarRating: " + starRating + ", Review: " + review);

                PerfumeReviewVO vo = new PerfumeReviewVO(perfumeNumber, userId, starRating, review);
                list.add(vo);

                // 출력한 후
                System.out.println("After inserting into VO - " + vo.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pStmt != null) pStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }


}
