package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.PerfumeReviewVO;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@Repository
public class PerfumeReviewDAO {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public boolean newReview(int perfumeNumber, String userId, int starRating, String review) {
        int result = 0;

        String checkDuplicateSql = "SELECT COUNT(*) FROM 한줄평 WHERE 향수번호 = ? AND 회원번호 = (SELECT 회원번호 FROM 회원 WHERE 아이디 = ?)";
        String insertSql = "INSERT INTO 한줄평 VALUES (?, (SELECT 회원번호 FROM 회원 WHERE 아이디 = ?), ?, ?)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(checkDuplicateSql);
            pStmt.setInt(1, perfumeNumber);
            pStmt.setString(2, userId);
            rs = pStmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    // 이미 해당 사용자의 평점이 존재하므로 중복으로 처리
                    return false;
                }
            }

            pStmt = conn.prepareStatement(insertSql);
            pStmt.setInt(1, perfumeNumber);
            pStmt.setString(2, userId);
            pStmt.setInt(3, starRating);
            pStmt.setString(4, review);

            result = pStmt.executeUpdate();
            System.out.println("평점 등록 DB 결과 확인: " + result);
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

        return result == 1;
    }

    public List<PerfumeReviewVO> viewReview(int perfumeNumber) {
        List<PerfumeReviewVO> list = new ArrayList<>();

        String sql = "SELECT 한줄평.*, 회원.아이디, 회원.이미지 FROM 한줄평 JOIN 회원 ON 한줄평.회원번호 = 회원.회원번호 WHERE 한줄평.향수번호= ?";

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
                String image = rs.getString("이미지");

                // 출력 전
                System.out.println("Before inserting into VO - PerfumeNum: " + perfumeNumber + ", UserId: " + userId + ", StarRating: " + starRating + ", Review: " + review + ",image" + image);

                PerfumeReviewVO vo = new PerfumeReviewVO(perfumeNumber, userId, starRating, review, image);
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

   /* public boolean updateReview(int perfumeNumber, String userId, int starRating, String review) {
        int result = 0;

        String sql = "UPDATE 한줄평 SET 별점 = ?, 한줄평 = ? WHERE 향수번호 = ? AND 회원번호 = (SELECT 회원번호 FROM 회원 WHERE 아이디 = ?)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, starRating);
            pStmt.setString(2, review);
            pStmt.setInt(3, perfumeNumber);
            pStmt.setString(4, userId);

            result = pStmt.executeUpdate();
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

        return result == 1;
    }*/

    public boolean deleteReview(int perfumeNumber, String userId) {
        int result = 0;

        String sql = "DELETE FROM 한줄평 WHERE 향수번호 = ? AND 회원번호 = (SELECT 회원번호 FROM 회원 WHERE 아이디 = ?)";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, perfumeNumber);
            pStmt.setString(2, userId);

            result = pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
                if (pStmt != null) pStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result == 1;
    }

    public PerfumeReviewVO viewReviewBeforeUpdate(int perfumeNumber, String userId) {
        PerfumeReviewVO review = null;

        String sql = "SELECT 한줄평.*, 회원.아이디, 회원.이미지 FROM 한줄평 JOIN 회원 ON 한줄평.회원번호 = 회원.회원번호 WHERE 한줄평.향수번호= ? AND 회원.아이디 = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, perfumeNumber);
            pStmt.setString(2, userId);
            rs = pStmt.executeQuery();

            if (rs.next()) {
                int starRating = rs.getInt("별점");
                String reviewContent = rs.getString("한줄평");
                String image = rs.getString("이미지");

                review = new PerfumeReviewVO(perfumeNumber, userId, starRating, reviewContent, image);
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

        return review;
    }
}