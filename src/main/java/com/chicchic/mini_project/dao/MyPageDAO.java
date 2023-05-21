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
                sql = "SELECT a.*, b.* FROM 게시글 a INNER JOIN 게시판 b ON a.게시판번호 = b.게시판번호 " +
                        "WHERE 회원번호 = " +
                        "(SELECT 회원번호 FROM 회원 " +
                        "WHERE 아이디 = '" + id + "')" +
                        "ORDER BY 게시글번호 DESC";
            }

            // 내 댓글
            else if(views == 2) {
                sql = "SELECT b.*, a.*, c.* " +
                        "FROM 게시글 a " +
                        "INNER JOIN 댓글 b ON a.게시글번호 = b.게시글번호 " +
                        "INNER JOIN 게시판 c ON a.게시판번호 = c.게시판번호 " +
                        "WHERE a.회원번호 = " +
                        "(SELECT 회원번호 FROM 회원 WHERE 아이디 = '" + id + "')";
            }

            // 내 좋아요
            else if(views == 3) {
                sql = "SELECT a.*, c.* " +
                        "FROM 게시글 a INNER JOIN 좋아요 b " +
                        "ON a.게시글번호 = b.게시글번호 " +
                        "INNER JOIN 게시판 c " +
                        "ON a.게시판번호 = c.게시판번호 " +
                        "WHERE b.회원번호 = " +
                        "(SELECT 회원번호 FROM 회원 WHERE 아이디 = '" + id + "') " +
                        "ORDER BY a.게시글번호 DESC";
            }

            // 내 한줄평
            else if(views == 4) {
                sql = "SELECT a.*, b.* " +
                        " from 한줄평 a inner join perfumes b " +
                        " on a.향수번호 = b.PERFUME_NUMBER " +
                        " WHERE 회원번호 = " +
                        " (SELECT 회원번호 FROM 회원 WHERE 아이디 = '" + id + "') ";
            }



            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if(views == 1) {
                    int anum = rs.getInt("게시글번호");
                    int bnum = rs.getInt("게시판번호");
                    int unum = rs.getInt("회원번호");
                    String title = rs.getString("제목");
                    String text = rs.getString("내용");
                    Date date = rs.getDate("작성일");
                    int viewCnt = rs.getInt("조회수");
                    int pLiked = rs.getInt("좋아요수");
                    String bname = rs.getString("게시판이름");

                    ArticleVO vo = new ArticleVO();

                    vo.setBname(bname);
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
                else if(views == 2) {
                    int anum = rs.getInt("게시글번호");
                    int bnum = rs.getInt("게시판번호");
                    int unum = rs.getInt("회원번호");
                    String title = rs.getString("제목");
                    String text = rs.getString("내용");
                    Date date = rs.getDate("작성일");
                    int viewCnt = rs.getInt("조회수");
                    int pLiked = rs.getInt("좋아요수");
                    String cmtText = rs.getString("내용");
                    Date cmtDate = rs.getDate("작성일");
                    int cmtNum = rs.getInt("댓글번호");
                    String bname = rs.getString("게시판이름");

                    ArticleVO vo = new ArticleVO();

                    vo.setBname(bname);
                    vo.setAnum(anum);
                    vo.setBnum(bnum);
                    vo.setUnum(unum);
                    vo.setTitle(title);
                    vo.setText(text);
                    vo.setDate(date);
                    vo.setView(viewCnt);
                    vo.setLike(pLiked);
                    vo.setCommentText(cmtText);
                    vo.setCommentDate(cmtDate);
                    vo.setCommentNum(cmtNum);
                    list.add(vo);
                } else if (views == 3) {

                    String title = rs.getString("제목");
                    Date date = rs.getDate("작성일");
                    int bnum = rs.getInt("게시판번호");
                    int pLiked = rs.getInt("좋아요수");
                    String bname = rs.getString("게시판이름");

                    ArticleVO vo = new ArticleVO();
                    vo.setBname(bname);
                    vo.setBnum(bnum);
                    vo.setTitle(title);
                    vo.setDate(date);
                    vo.setLike(pLiked);
                    list.add(vo);
                }
                else {

                    String perfumeName = rs.getString("NAME");
                    String oneLineText = rs.getString("한줄평");
                    int star = rs.getInt("별점");

                    ArticleVO vo = new ArticleVO();
                    vo.setPerfumeName(perfumeName);
                    vo.setOneLineText(oneLineText);
                    vo.setStar(star);
                    list.add(vo);
                }
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


