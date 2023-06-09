package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MemberDAO {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    // 로그인 체크
    public boolean loginCheck(String id, String pwd) {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기
            String sql = "SELECT * FROM 회원 WHERE 아이디 = " + "'" + id + "'";
            rs = stmt.executeQuery(sql);

            while(rs.next()) { // 읽은 데이타가 있으면 true
                String sqlId = rs.getString("아이디"); // 쿼리문 수행 결과에서 ID값을 가져 옴
                String sqlPwd = rs.getString("비밀번호");
                System.out.println("ID : " + sqlId);
                System.out.println("PWD : " + sqlPwd);
                if(id.equals(sqlId) && pwd.equals(sqlPwd)) {
                    Common.close(rs);
                    Common.close(stmt);
                    Common.close(conn);
                    return true;
                }
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUserImage(String id) {
        String sql = "SELECT 이미지 FROM 회원 WHERE 아이디 = ? ";
        String userImage = null;
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);

            rs = pStmt.executeQuery();
            if (rs.next()) {
                userImage = rs.getString("이미지");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(pStmt);
        Common.close(conn);

        return userImage;
    }

    // 회원 조회
    public List<MemberVO> memberSelect() {
        List<MemberVO> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement(); // Statement 객체 얻기
            String sql = "SELECT * FROM 회원";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String id = rs.getString("아이디");
                String pwd = rs.getString("비밀번호");
                String name = rs.getString("이름");
                String email = rs.getString("이메일");

                MemberVO vo = new MemberVO();
                vo.setId(id);
                vo.setPwd(pwd);
                vo.setName(name);
                vo.setEmail(email);
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

    // 가입 여부 확인
    public boolean regMemberCheck(String id) {
        boolean isNotReg = false;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM 회원 WHERE 아이디 = " + "'" + id +"'";
            rs = stmt.executeQuery(sql);
            if(rs.next()) isNotReg = false;
            else isNotReg = true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        Common.close(rs);
        Common.close(stmt);
        Common.close(conn);
        return isNotReg; // 가입 되어 있으면 false, 가입이 안되어 있으면 true
    }

    // 회원 가입

    public boolean memberRegister(String id, String pwd, String name, String email) {
        int result = 0;
        String sql = "INSERT INTO 회원(회원번호, 이름, 아이디, 비밀번호, 이메일) VALUES(회원번호.NEXTVAL, ?, ?, ?, ?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, name);
            pStmt.setString(2, id);
            pStmt.setString(3, pwd);
            pStmt.setString(4, email);
            result = pStmt.executeUpdate();
            System.out.println("회원 가입 DB 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if(result == 1) return true;
        else return false;
    }

    public boolean memberDelete(String id) { // 회원삭제
        int result = 0;
        String sql = "DELETE FROM 회원 WHERE 아이디 = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            result = pStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        System.out.println("회원탈퇴 성공");
        if(result == 1) return true;
        else return false;
    }

    public boolean plusThreePoint(String id) {
        int result = 0;
        String sql = "UPDATE 회원 SET 회원점수 = 회원점수 + 3  WHERE 아이디 = ? ";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            result = pStmt.executeUpdate();
            System.out.println("3점 증가 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }

    public boolean plusOnePoint(String id) {
        int result = 0;
        String sql = "UPDATE 회원 SET 회원점수 = 회원점수 + 1  WHERE 아이디 = ? ";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            result = pStmt.executeUpdate();
            System.out.println("1점 증가 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }

    public boolean myGrade(String id) {
        int result = 0;
        String sql = "UPDATE 회원 SET 회원등급 = 회원점수 / 10  WHERE 아이디 = ? ";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, id);
            result = pStmt.executeUpdate();
            System.out.println("회원등급 갱신 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }

    public boolean updatePw(String id, String pwd) {
        int result = 0;
        String sql = "UPDATE 회원 SET 비밀번호 = ? WHERE 아이디 = ?";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, pwd);
            pStmt.setString(2, id);

            result = pStmt.executeUpdate();
            System.out.println("비밀번호 수정 DB 결과 확인 : " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }

}
