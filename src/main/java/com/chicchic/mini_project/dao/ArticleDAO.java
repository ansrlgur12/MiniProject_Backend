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


    public List<ArticleVO> articleList(int num, int view) {
        String sql = null;
        List<ArticleVO> list = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기

            if (view == 1) {
                sql = "SELECT a.*, m.아이디 " +
                        "FROM 게시글 a " +
                        "INNER JOIN 회원 m ON a.회원번호 = m.회원번호 " +
                        "WHERE a.게시판번호 = " + num +
                        "ORDER BY a.조회수 DESC";
            }
            else if (view == 2) {
                sql = "SELECT a.*, m.아이디 " +
                        "FROM 게시글 a " +
                        "INNER JOIN 회원 m ON a.회원번호 = m.회원번호 " +
                        "WHERE a.게시판번호 = " + num +
                        "ORDER BY a.좋아요수 DESC";

            } else sql = "SELECT a.*, m.아이디 " +
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
                String img = rs.getString("이미지");

                ArticleVO vo = new ArticleVO();
                vo.setAnum(anum);
                vo.setBnum(bnum);
                vo.setTitle(title);
                vo.setText(text);
                vo.setUnum(unum);
                vo.setDate(date);
                vo.setId(id);
                vo.setImg(img);
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

    public List<ArticleVO> smallArticleList(int num) {
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

            String sql = "SELECT a.*, m.아이디, m.회원등급 " +
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
                int view = rs.getInt("조회수");
                int like = rs.getInt("좋아요수");
                String img = rs.getString("이미지");
                int userGrade = rs.getInt("회원등급");

                ArticleVO vo = new ArticleVO();
                vo.setAnum(anum);
                vo.setBnum(bnum);
                vo.setTitle(title);
                vo.setText(text);
                vo.setUnum(unum);
                vo.setDate(date);
                vo.setId(id);
                vo.setImg(img);
                vo.setView(view);
                vo.setLike(like);
                vo.setUserGrade(userGrade);
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


    public boolean newArticle(String id, int bnum, String title, String text, String pwd, String img) {
        int result = 0;
        String sql = "INSERT INTO 게시글 VALUES(게시글번호.NEXTVAL, ?, (SELECT 회원번호 \" +\n" +
                "                \"        FROM 회원 \" +\n" +
                "                \"     WHERE 아이디 = ?), ?, ?, ?, SYSDATE, ?, 'tag', 1, 0)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, bnum);
            pStmt.setString(2, id);
            pStmt.setString(3, title);
            pStmt.setString(4, pwd);
            pStmt.setString(5, text);
            pStmt.setString(6, img);

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

    public boolean update(int anum, int bnum, String title, String text, String pwd, String img) {
        int result = 0;
        String sql = "UPDATE 게시글 SET 게시판번호 = ?, 제목 = ?, 비밀번호 = ?, 내용 = ?, 이미지 = ? WHERE 게시글번호 = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, bnum);
            pStmt.setString(2, title);
            pStmt.setString(3, pwd);
            pStmt.setString(4, text);
            pStmt.setString(5, img);
            pStmt.setInt(6, anum);

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


    public boolean newComment(int anum, String id, String text, String pwd) {
        int result = 0;
        String sql = "INSERT INTO 댓글 VALUES(댓글번호.NEXTVAL, ?, " +
                "(SELECT 회원번호 " +
                "FROM 회원 " +
                "WHERE 아이디 = ? ), ?, ?, SYSDATE)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, anum);
            pStmt.setString(2, id);
            pStmt.setString(3, text);
            pStmt.setString(4, pwd);

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

//            String sql = "SELECT a.*, m.아이디, m.이미지 " +
//                    "FROM 댓글 a " +
//                    "INNER JOIN 회원 m ON a.회원번호 = m.회원번호 " +
//                    "WHERE a.게시글번호 = " + num +
//                    "ORDER BY a.댓글번호";
            String sql = "SELECT a.댓글번호, a.게시글번호, a.회원번호, a.내용, a.비밀번호, m.아이디, m.이미지, " +
                    "CASE " +
                    "    WHEN SYSDATE - a.작성일 < 1/24/60 THEN FLOOR((SYSDATE - a.작성일) * 24 * 60 * 60) || '초 전' " +
                    "    WHEN SYSDATE - a.작성일 < 1/24 THEN FLOOR((SYSDATE - a.작성일) * 24 * 60) || '분 전' " +
                    "    WHEN SYSDATE - a.작성일 < 1 THEN FLOOR((SYSDATE - a.작성일) * 24) || '시간 전' " +
                    "    ELSE TO_CHAR(a.작성일, 'YYYY-MM-DD') " +
                    "END AS 작성일 " +
                    "FROM 댓글 a " +
                    "INNER JOIN 회원 m ON a.회원번호 = m.회원번호 " +
                    "WHERE a.게시글번호 = " + num +
                    " ORDER BY a.댓글번호 ";


            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int commentNum = rs.getInt("댓글번호");
                int anum = rs.getInt("게시글번호");
                int unum = rs.getInt("회원번호");
                String commentText = rs.getString("내용");
                String commentPwd = rs.getString("비밀번호");
                String id = rs.getString("아이디");
                String userImg = rs.getString("이미지");
                String date2 = rs.getString("작성일");

                ArticleVO vo = new ArticleVO();
                vo.setCommentNum(commentNum);
                vo.setAnum(anum);
                vo.setCommentText(commentText);
                vo.setCommentPwd(commentPwd);
                vo.setUnum(unum);
                vo.setDate2(date2);
                vo.setId(id);
                vo.setUserImg(userImg);
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

            System.out.println("댓글삭제완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public List<ArticleVO> viewComment(int num) { //한줄평 보여주는 코드
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

    public boolean view(int view) {
        int result = 0;
        String sql = "UPDATE 게시글 SET 조회수 = 조회수 + 1 WHERE 게시글번호 = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, view);

            result = pStmt.executeUpdate();
            System.out.println("조회수 증가 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }

    public boolean like(int anum, String id) {
        int result = 0;
        String sql = "INSERT INTO 좋아요 VALUES(?, " +
                "(SELECT 회원번호 " +
                "FROM 회원 " +
                "WHERE 아이디 = ? )) ";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, anum);
            pStmt.setString(2, id);

            result = pStmt.executeUpdate();
            System.out.println("조회수 증가 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }

    public boolean dislike(int anum, String id) {
        int result = 0;
        String sql = " DELETE FROM 좋아요 WHERE 게시글번호 = ? AND 회원번호 = (SELECT 회원번호 FROM 회원 WHERE 아이디 = ? )  ";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, anum);
            pStmt.setString(2, id);

            result = pStmt.executeUpdate();
            System.out.println("좋아요 취소 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }

    public void deleteLike(int anum) {
        String sql = "DELETE FROM 좋아요 WHERE 게시글번호 = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, anum);
            pStmt.executeUpdate();
            System.out.println("좋아요 삭제 ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public int countLike(String id, int anum) {
        String sql = "SELECT COUNT(*) FROM 좋아요 WHERE 회원번호 = (SELECT 회원번호 FROM 회원 WHERE 아이디 = ? ) AND 게시글번호 = ?";
        int count = 0;
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            pStmt.setInt(2, anum);
            try (ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
            System.out.println("카운트 : " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        return count;
    }

    public boolean plusLike(int anum) {
        int result = 0;
        String sql = "UPDATE 게시글 SET 좋아요수 = 좋아요수 + 1  WHERE 게시글번호 = ? ";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, anum);
            result = pStmt.executeUpdate();
            System.out.println("좋아요 증가 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }

    public boolean minusLike(int anum) {
        int result = 0;
        String sql = "UPDATE 게시글 SET 좋아요수 = 좋아요수 - 1  WHERE 게시글번호 = ? ";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, anum);
            result = pStmt.executeUpdate();
            System.out.println("좋아요 감소 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }

    public void deleteCommentAll(int anum) {
        String sql = "DELETE FROM 댓글 WHERE 게시글번호 = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, anum);
            pStmt.executeUpdate();
            System.out.println("댓글 전체 삭제 ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public int isUser(String id, int anum) {
        String sql = "SELECT COUNT(*) FROM 게시글 WHERE 회원번호 = (SELECT 회원번호 FROM 회원 WHERE 아이디 = ? ) AND 게시글번호 = ?";
        int count = 0;
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            pStmt.setInt(2, anum);
            try (ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
            System.out.println("본인작성글 : " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        return count;
    }

    public int commentMatch(String id, int commentNum, int anum) {
        String sql = "SELECT COUNT(*) FROM 댓글 WHERE 회원번호 = (SELECT 회원번호 FROM 회원 WHERE 아이디 = ? ) AND 댓글번호 = ? AND 게시글번호 = ? ";
        int count = 0;
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            pStmt.setInt(2, commentNum);
            pStmt.setInt(3, anum);
            try (ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
            System.out.println("본인작성댓글 : " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        return count;
    }

    public List<ArticleVO> searchArticle(String searchText) {
        List<ArticleVO> list = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기

            String sql = "SELECT a.*, m.아이디 FROM 게시글 a INNER JOIN 회원 m ON a.회원번호 = m.회원번호 " +
                    "WHERE a.내용 LIKE '%" + searchText + "%' OR a.제목 LIKE '%" + searchText + "%'";

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int anum = rs.getInt("게시글번호");
                int bnum = rs.getInt("게시판번호");
                String title = rs.getString("제목");
                String text = rs.getString("내용");
                int unum = rs.getInt("회원번호");
                Date date = rs.getDate("작성일");
                String id = rs.getString("아이디");
                String img = rs.getString("이미지");

                ArticleVO vo = new ArticleVO();
                vo.setAnum(anum);
                vo.setBnum(bnum);
                vo.setTitle(title);
                vo.setText(text);
                vo.setUnum(unum);
                vo.setDate(date);
                vo.setId(id);
                vo.setImg(img);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
            System.out.println("DAO 게시글 검색");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 공지사항, news 글쓰기
    public boolean newNotice(int bnum, String id, String title, String text, String pwd, String img) {
        int result = 0;
        String sql = "INSERT INTO 게시글 VALUES(게시글번호.NEXTVAL, ?, (SELECT 회원번호 \" +\n" +
                "                \"        FROM 회원 \" +\n" +
                "                \"     WHERE 아이디 = ?), ?, ?, ?, SYSDATE, ?, 'tag', 1, 0)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, bnum);
            pStmt.setString(2, id);
            pStmt.setString(3, title);
            pStmt.setString(4, pwd);
            pStmt.setString(5, text);
            pStmt.setString(6, img);

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
}

