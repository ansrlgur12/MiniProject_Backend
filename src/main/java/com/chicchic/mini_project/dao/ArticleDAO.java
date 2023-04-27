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


    public List<ArticleVO> article(int num) {
        String sql = null;
        List<ArticleVO> list = new ArrayList<>();
        try{
            conn = getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기

            if (num == 0) sql = "SELECT * FROM 게시글";
            else sql = "SELECT * FROM 게시글 WHERE 게시판번호 = " + num ;

            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int anum = rs.getInt("게시글번호");
                int bnum = rs.getInt("게시판번호");
                String title = rs.getString("제목");
                String text = rs.getString("내용");
                int unum = rs.getInt("회원번호");
                Date date = rs.getDate("작성일");

                ArticleVO vo = new ArticleVO();
                vo.setAnum(anum);
                vo.setBnum(bnum);
                vo.setTitle(title);
                vo.setText(text);
                vo.setUnum(unum);
                vo.setDate(date);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }


    public List<ArticleVO> write(int num) {
        List<ArticleVO> list = new ArrayList<>();
        try{
            conn = getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기

            String sql = "SELECT * FROM 게시글 WHERE 게시글번호 = " + num ;

            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int anum = rs.getInt("게시글번호");
                int bnum = rs.getInt("게시판번호");
                String title = rs.getString("제목");
                String text = rs.getString("내용");
                int unum = rs.getInt("회원번호");
                Date date = rs.getDate("작성일");

                ArticleVO vo = new ArticleVO();
                vo.setAnum(anum);
                vo.setBnum(bnum);
                vo.setTitle(title);
                vo.setText(text);
                vo.setUnum(unum);
                vo.setDate(date);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
