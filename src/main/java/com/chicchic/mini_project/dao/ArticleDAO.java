package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.ArticleVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;


    public List<ArticleVO> article() {
        List<ArticleVO> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기
            String sql = "SELECT * FROM T_MEMBER";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int anum = rs.getInt("게시글번호");
                String title = rs.getString("제목");
                String text = rs.getString("내용");
                int unum = rs.getInt("회원번호");
                Date date = rs.getDate("작성일");

                ArticleVO vo = new ArticleVO();
                vo.setAnum(anum);
                vo.setUnum(unum);
                vo.setTitle(title);
                vo.setText(text);
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
