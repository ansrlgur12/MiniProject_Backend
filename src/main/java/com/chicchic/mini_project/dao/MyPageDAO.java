package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.ArticleVO;

import java.sql.*;
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

            String sql = "SELECT * " +
                    "FROM 회원" +
                    " WHERE 아이디 = '" + id + "'";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String userImg  = rs.getString("이미지");
                int userGrade = rs.getInt("회원등급");
                ArticleVO vo = new ArticleVO();
                vo.setUserImg(userImg);
                vo.setUserGrade(userGrade);
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

    // 마이페이지 내가 쓴 리뷰
    public List<ArticleVO> myHistory(String id, int views) {
        String sql = null;
        List<ArticleVO> list = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            // 내 리뷰
            if(views == 1) {
                sql = "SELECT * FROM 게시글 " +
                        "WHERE 회원번호 = " +
                        "(SELECT 회원번호 FROM 회원 " +
                        "WHERE 아이디 = '" + id + "')" +
                        "ORDER BY 게시글번호 DESC";
            }

            // 내 댓글
            else if(views == 2) {
                sql = "SELECT b.*, a.* " +
                        "FROM 게시글 a INNER JOIN 댓글 b " +
                        "ON a.게시글번호 = b.게시글번호 " +
                        "WHERE a.회원번호 = " +
                        "(SELECT 회원번호 FROM 회원 WHERE 아이디 = '" + id + "')";
            }

            // 내 좋아요
            else if(views == 3) {
                sql = "SELECT a.게시판번호, a.제목, a.좋아요수, a.작성일 " +
                        "FROM 게시글 a INNER JOIN 좋아요 b " +
                        "ON a.게시글번호 = b.게시글번호 " +
                        "WHERE a.회원번호 = " +
                        "(SELECT 회원번호 FROM 회원 WHERE 아이디 = '" + id + "') " +
                        "ORDER BY a.게시글번호 DESC";
            }
            // 내 한줄평
            else if(views == 4) {
                sql = "SELECT * FROM 한줄평 " +
                        "WHERE 회원번호 = " +
                        "(SELECT 회원번호 FROM 회원 " +
                        "WHERE 아이디 = '" + id + "')";
            }

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int anum = rs.getInt("게시글번호");
                int bnum = rs.getInt("게시판번호");
                int unum = rs.getInt("회원번호");
                String title = rs.getString("제목");
                String text = rs.getString("내용");
                Date date = rs.getDate("작성일");
                int viewCnt = rs.getInt("조회수");
                int pLiked = rs.getInt("좋아요수");
//                String cmtText = rs.getString("댓글내용");
//                Date cmtDate = rs.getDate("댓글작성일");
//                int cmtNum = rs.getInt("댓글번호");

                ArticleVO vo = new ArticleVO();

                vo.setAnum(anum);
                vo.setBnum(bnum);
                vo.setUnum(unum);
                vo.setTitle(title);
                vo.setText(text);
                vo.setDate(date);
                vo.setView(viewCnt);
                vo.setLike(pLiked);
//                vo.setCommentText(cmtText);
//                vo.setCommentDate(cmtDate);
//                vo.setCommentNum(cmtNum);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

 /*
 // 리뷰
    public List<ArticleVO> myReview(String id) {

        List<ArticleVO> list = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            // 내 리뷰
            String sql = "SELECT * FROM 게시글 " +
                        "WHERE 회원번호 = " +
                        "(SELECT 회원번호 FROM 회원 " +
                        "WHERE 아이디 = '" + id + "')" +
                        "ORDER BY 게시글번호 DESC";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int anum = rs.getInt("게시글번호");
                int bnum = rs.getInt("게시판번호");
                int unum = rs.getInt("회원번호");
                String title = rs.getString("제목");
                String text = rs.getString("내용");
                Date date = rs.getDate("작성일");
                int viewCnt = rs.getInt("조회수");
                int pLiked = rs.getInt("좋아요수");

                ArticleVO vo = new ArticleVO();

                vo.setAnum(anum);
                vo.setBnum(bnum);
                vo.setUnum(unum);
                vo.setTitle(title);
                vo.setText(text);
                vo.setDate(date);
                vo.setView(viewCnt);
                vo.setLike(pLiked);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

// 댓글
    public List<ArticleVO> myComment(String id) {
        List<ArticleVO> list = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT b.*, a.* " +
                        "FROM 게시글 a INNER JOIN 댓글 b " +
                        "ON a.게시글번호 = b.게시글번호 " +
                        "WHERE a.회원번호 = " +
                        "(SELECT 회원번호 FROM 회원 WHERE 아이디 = '" + id + "')" +
                        "ORDER BY a.댓글번호 DESC";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int anum = rs.getInt("게시글번호");
                int bnum = rs.getInt("게시판번호");
                int unum = rs.getInt("회원번호");
                String title = rs.getString("제목");
                Date date = rs.getDate("작성일");
                String cmtText = rs.getString("댓글내용");
                Date cmtDate = rs.getDate("댓글작성일");
                int cmtNum = rs.getInt("댓글번호");

                ArticleVO vo = new ArticleVO();

                vo.setAnum(anum);
                vo.setBnum(bnum);
                vo.setUnum(unum);
                vo.setTitle(title);
                vo.setDate(date);
                vo.setCommentText(cmtText);
                vo.setCommentDate(cmtDate);
                vo.setCommentNum(cmtNum);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
// 좋아요
    public List<ArticleVO> myLike(String id) {
        List<ArticleVO> list = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT a.게시판번호, a.제목, a.좋아요수, a.작성일 " +
                    "FROM 게시글 a INNER JOIN 좋아요 b " +
                    "ON a.게시글번호 = b.게시글번호 " +
                    "WHERE a.회원번호 = " +
                    "(SELECT 회원번호 FROM 회원 WHERE 아이디 = '" + id + "') " +
                    "ORDER BY a.게시글번호 DESC";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int anum = rs.getInt("게시글번호");
                int bnum = rs.getInt("게시판번호");
                int unum = rs.getInt("회원번호");
                String title = rs.getString("제목");
                Date date = rs.getDate("작성일");
                int pLiked = rs.getInt("좋아요수");

                ArticleVO vo = new ArticleVO();

                vo.setAnum(anum);
                vo.setBnum(bnum);
                vo.setUnum(unum);
                vo.setTitle(title);
                vo.setDate(date);
                vo.setLike(pLiked);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
// 한줄평(임시)
    public List<ArticleVO> myOneLine(String id) {
        List<ArticleVO> list = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM 한줄평";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int anum = rs.getInt("게시글번호");
                int bnum = rs.getInt("게시판번호");
                int unum = rs.getInt("회원번호");
                String title = rs.getString("제목");
                String text = rs.getString("내용");
                Date date = rs.getDate("작성일");
                int viewCnt = rs.getInt("조회수");
                int pLiked = rs.getInt("좋아요수");

                ArticleVO vo = new ArticleVO();

                vo.setAnum(anum);
                vo.setBnum(bnum);
                vo.setUnum(unum);
                vo.setTitle(title);
                vo.setText(text);
                vo.setDate(date);
                vo.setView(viewCnt);
                vo.setLike(pLiked);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

 */
}
