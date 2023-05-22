package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.EventVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public List<EventVO> eventList(int eNum, int view) {
        String sql = null;
        List<EventVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();

            if (view == 2) {
                sql = "SELECT * FROM 이벤트 " +
                        "WHERE 시작일 < SYSDATE " +
                        "AND 종료일 > SYSDATE " +
                        "ORDER BY 이벤트번호";
            }
            else if (view == 3){
                sql = "SELECT * FROM 이벤트 " +
                        "WHERE 시작일 > SYSDATE " +
                        "ORDER BY 이벤트번호";
            }
            else if (view == 4){
                sql = "SELECT * FROM 이벤트 " +
                        "WHERE 종료일 < SYSDATE " +
                        "ORDER BY 이벤트번호";
            }
            else sql = "SELECT * FROM 이벤트 " +
                        "ORDER BY 이벤트번호";

            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int eventNum = rs.getInt("이벤트번호");
                String eTitle = rs.getString("제목");
                String eText = rs.getString("내용");
                String eImg = rs.getString("이미지");
                Date startDate = rs.getDate("시작일");
                Date endDate = rs.getDate("종료일");

                EventVO vo = new EventVO();
                vo.setEventNum(eventNum);
                vo.setEventTitle(eTitle);
                vo.setEventText(eText);
                vo.setEventImg(eImg);
                vo.setStartEvent(startDate);
                vo.setEndEvent(endDate);
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
    public List<EventVO> eventDesc(int eNum) {
        List<EventVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM 이벤트 WHERE 이벤트번호 = " + eNum;
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int eventNum = rs.getInt("이벤트번호");
                String eTitle = rs.getString("제목");
                String eText = rs.getString("내용");
                String eImg = rs.getString("이미지");
                Date startDate = rs.getDate("시작일");
                Date endDate = rs.getDate("종료일");

                EventVO vo = new EventVO();
                vo.setEventNum(eventNum);
                vo.setEventTitle(eTitle);
                vo.setEventText(eText);
                vo.setEventImg(eImg);
                vo.setStartEvent(startDate);
                vo.setEndEvent(endDate);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // 이벤트 등록
    public boolean newEvent(int eventNum, String eventTitle, String eventText, String eventImg, Date startEvent, Date endEvent) {
        int result = 0;
        String sql = "INSERT INTO 이벤트 (이벤트번호, 제목, 내용, 이미지, 시작일, 종료일) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, eventNum);
            pStmt.setString(2,eventTitle);
            pStmt.setString(3, eventText);
            pStmt.setString(4, eventImg);
            pStmt.setDate(5, startEvent);
            pStmt.setDate(6, endEvent);

            result = pStmt.executeUpdate();
            System.out.println("이벤트 등록 DB 결과 : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

        if (result == 1) return true;
        else return false;
    }

}
