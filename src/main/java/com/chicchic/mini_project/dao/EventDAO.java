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

    public List<EventVO> eventDesc() {
        List<EventVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM 이벤트";
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                int eventNum = rs.getInt("이벤트번호");
                String eTitle = rs.getString("제목");

                EventVO vo = new EventVO();
                vo.setEventNum(eventNum);
                vo.setEventTitle(eTitle);
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
}
