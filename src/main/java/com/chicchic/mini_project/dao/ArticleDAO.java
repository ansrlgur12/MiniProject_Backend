package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.ArticleVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.chicchic.mini_project.common.Common.getConnection;

public class ArticleDAO {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;


    public List<ArticleVO> articleList(int num) {
        String sql = null;
        List<ArticleVO> list = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기

            if (num == 0) sql = "SELECT * FROM 게시글";
            else sql = "SELECT a.*, m.아이디 " +
                    "FROM 게시글 a " +
                    "INNER JOIN 회원 m ON a.회원번호 = m.회원번호 " +
                    "WHERE a.게시판번호 = " + num +
                    "ORDER BY a.게시글번호 DESC";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int anum = rs.getInt("게시글번호");
                int bnum = rs.getInt("게시판번호");
                String title = rs.getString("제목");
                String text = rs.getString("내용");
                int unum = rs.getInt("회원번호");
                Date date = rs.getDate("작성일");
                String id = rs.getString("아이디");

                ArticleVO vo = new ArticleVO();
                vo.setAnum(anum);
                vo.setBnum(bnum);
                vo.setTitle(title);
                vo.setText(text);
                vo.setUnum(unum);
                vo.setDate(date);
                vo.setId(id);
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


    public List<ArticleVO> article(int num) {
        List<ArticleVO> list = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기

            String sql = "SELECT a.*, m.아이디 " +
                    "FROM 게시글 a " +
                    "INNER JOIN 회원 m ON a.회원번호 = m.회원번호 " +
                    "WHERE a.게시글번호 = " + num;

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int anum = rs.getInt("게시글번호");
                int bnum = rs.getInt("게시판번호");
                String title = rs.getString("제목");
                String text = rs.getString("내용");
                int unum = rs.getInt("회원번호");
                Date date = rs.getDate("작성일");
                String id = rs.getString("아이디");

                ArticleVO vo = new ArticleVO();
                vo.setAnum(anum);
                vo.setBnum(bnum);
                vo.setTitle(title);
                vo.setText(text);
                vo.setUnum(unum);
                vo.setDate(date);
                vo.setId(id);
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


    public boolean newArticle(int bnum, String title, String text, String pwd) {
        int result = 0;
        String sql = "INSERT INTO 게시글 VALUES(게시글번호.NEXTVAL, ?, 1, 1, ?, ?, ?, SYSDATE, 'image', 'tag')";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, bnum);
            pStmt.setString(2, title);
            pStmt.setString(3, pwd);
            pStmt.setString(4, text);

            result = pStmt.executeUpdate();
            System.out.println("게시글 등록 DB 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }


    //    public List<ArticleVO> delete(int num) {
//        List<ArticleVO> list = new ArrayList<>();
//        String sql = "DELETE FROM 게시글 WHERE 게시글번호 = ?";
//        try{
//            conn = Common.getConnection();
//            pStmt = conn.prepareStatement(sql);
//            pStmt.setInt(1,num);
//
//            ArticleVO vo = new ArticleVO();
//            vo.setAnum(anum);
//            list.add(vo);
//
//            Common.close(rs);
//            Common.close(stmt);
//            Common.close(conn);
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return list;
//    }
    public void delete(int anum) {
        String sql = "DELETE FROM 게시글 WHERE 게시글번호 = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, anum);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean update(int anum, int bnum, String title, String text, String pwd) {
        int result = 0;
        String sql = "UPDATE 게시글 SET 게시판번호=?, 제목=?, 비밀번호=?, 내용=? WHERE 게시글번호=?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, bnum);
            pStmt.setString(2, title);
            pStmt.setString(3, pwd);
            pStmt.setString(4, text);
            pStmt.setInt(5, anum);

            result = pStmt.executeUpdate();
            System.out.println("게시글 수정 DB 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }
    public boolean newComment(int anum, String text, String pwd) {
        int result = 0;
        String sql = "INSERT INTO 댓글 VALUES(댓글번호.NEXTVAL, ?, 1, ?, ?, SYSDATE)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, anum);
            pStmt.setString(2, text);
            pStmt.setString(3, pwd);

            result = pStmt.executeUpdate();
            System.out.println("댓글 등록 DB 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }

    public List<ArticleVO> commentList(int num) {
        List<ArticleVO> list = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기

            String sql = "SELECT a.*, m.아이디 " +
                    "FROM 댓글 a " +
                    "INNER JOIN 회원 m ON a.회원번호 = m.회원번호 " +
                    "WHERE a.게시글번호 = " + num +
                    "ORDER BY a.댓글번호";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int commentNum = rs.getInt("댓글번호");
                int anum = rs.getInt("게시글번호");
                int unum = rs.getInt("회원번호");
                String commentText = rs.getString("내용");
                String commentPwd = rs.getString("비밀번호");
                Date date = rs.getDate("작성일");
                String id = rs.getString("아이디");

                ArticleVO vo = new ArticleVO();
                vo.setCommentNum(commentNum);
                vo.setAnum(anum);
                vo.setCommentText(commentText);
                vo.setCommentPwd(commentPwd);
                vo.setUnum(unum);
                vo.setDate(date);
                vo.setId(id);
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

    public void deleteComment(int commentNum) {
        String sql = "DELETE FROM 댓글 WHERE 댓글번호 = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, commentNum);
            pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ArticleVO> viewComment(int num) {
        List<ArticleVO> list = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기

            String sql = "SELECT * " +
                    "FROM 댓글" +
                    " WHERE 댓글번호 = " + num;

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int commentNum = rs.getInt("댓글번호");
                String commentText = rs.getString("내용");

                ArticleVO vo = new ArticleVO();
                vo.setCommentNum(commentNum);
                vo.setCommentText(commentText);
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

    public boolean updateComment(int commentNum, String text, String pwd) {
        int result = 0;
        String sql = "UPDATE 댓글 SET 내용=?, 비밀번호=? WHERE 댓글번호=?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, text);
            pStmt.setString(2, pwd);
            pStmt.setInt(3, commentNum);

            result = pStmt.executeUpdate();
            System.out.println("댓글 수정 DB 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }
}
