package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.PerfumeVO;
import com.chicchic.mini_project.vo.PerfumesVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoteFinderDAO {

    private Connection conn = null;

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public List<PerfumesVO> NoteFinderResult(String[] ids) {
        List<PerfumesVO> perfumes = new ArrayList<>();
        try {
            conn = Common.getConnection();
            String sql = "SELECT * FROM PERFUMES WHERE top_notes LIKE ? AND heart_notes LIKE ? AND base_notes LIKE ?";

            pStmt = conn.prepareStatement(sql);

            for (int i = 0; i < ids.length; i++) {
                pStmt.setString(i + 1, "%" + ids[i] + "%");
                System.out.println("Bound value for parameter " + (i + 1) + ": " + "%" + ids[i] + "%");

            }
            rs = pStmt.executeQuery();
            while (rs.next()) {
                PerfumesVO perfumesVO = new PerfumesVO();
                perfumesVO.setPerfumeNumber(rs.getLong("perfume_number"));
                perfumesVO.setThumbnail(rs.getString("thumbnail"));
                perfumesVO.setName(rs.getString("name"));
                perfumes.add(perfumesVO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(rs);
            Common.close(conn);
            Common.close(pStmt);
        }
        return perfumes;

    }
}
