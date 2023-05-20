package com.chicchic.mini_project.dao;

import com.chicchic.mini_project.common.Common;
import com.chicchic.mini_project.vo.PerfumesVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;


public class ImageTestDAO {


    private Connection conn = null;

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;
    private final String TABLE_NAME = "perfumes p\n" +
            "JOIN perfume_seasons s ON p.perfume_number = s.perfume_number\n" +
            "JOIN base_notes b ON p.perfume_number = b.perfume_number\n" +
            "JOIN perfume_category c ON p.perfume_number = c.perfume_number\n";

    public PerfumesVO getPerfumesByConditions(Integer[] selected) {
        StringJoiner whereClause = new StringJoiner(" AND ", " WHERE ", "");


        String[] conditions = {
                "s.seasons =1 OR s.seasons =2 OR s.seasons =3 OR s.seasons  =4",
                "(c.category_number LIKE =1 or  c.category_number LIKE =2 or c.category_number LIKE =3 or c.category_number =4 or c.category_number=5) OR (c.category_number= 6 or  c.category_number= 7 or c.category_number= 8 or c.category_number= 9 or c.category_number= 10)",
                "p.brand IN (80,1871,1328,1506,1517,1525,1543,1623,1631,1708,921,1718,1724,1816,1934,1977,2104,2208,2415,2706,1054,315, 749, 614,647, 1745, 855, 555, 653,534, 524,528, 3032,3130,3140,3227) OR p.brand NOT IN (80,1871,1328,1506,1517,1525,1543,1623,1631,1708,921,1718,1724,1816,1934,1977,2104,2208,2415,2706,1054,315, 749, 614,647, 1745, 855, 555, 653,534, 524,528, 3032,3130,3140,3227)",
                "b.note_number IS NOT NULL OR b.note_number IS NULL",
                "p.gender = 1 OR p.gender = 0 OR p.gender  IS NOT NULL",
                "c.category_number= 10 OR (c.category_number = 1 OR c.category_number = 2) OR (c.category_number = 6 OR c.category_number = 7) OR (c.category_number = 3 OR c.category_number = 4)"
        };

        for (int i = 0; i < selected.length; i++) {
            switch (i) {
                case 0: // season
                    if (selected[i] == 1) {
                        whereClause.add(conditions[i].split(" OR ")[0]);

                    } else if (selected[i] == 2) {
                        whereClause.add(conditions[i].split(" OR ")[1]);

                    } else if (selected[i] == 3) {
                        whereClause.add(conditions[i].split(" OR ")[2]);

                    } else if (selected[i] == 4) {
                        whereClause.add(conditions[i].split(" OR ")[3]);

                    }
                    break;
                case 1: // categories
                    if (selected[i] == 1) {
                        whereClause.add("(c.category_number= 1 or  c.category_number= 2 or c.category_number= 3 or c.category_number= 4 or c.category_number= 5)");

                    } else if (selected[i] == 2) {
                        whereClause.add("(c.category_number= 6 or  c.category_number= 7 or c.category_number= 8 or c.category_number= 9 or c.category_number= 10)");

                    }
                    break;
                case 2: // brand
                    if (selected[i] == 1) {
                        whereClause.add(conditions[i].split(" OR ")[0]);
                    } else if (selected[i] == 2) {
                        whereClause.add(conditions[i].split(" OR ")[1]);
                    }
                    break;
                case 3: // base_notes
                    if (selected[i] == 1) {
                        whereClause.add(conditions[i].split(" OR ")[0]);
                    } else if (selected[i] == 2) {
                        whereClause.add(conditions[i].split(" OR ")[1]);
                    }
                    break;
                case 4: // gender
                    if (selected[i] == 1) {
                        whereClause.add(conditions[i].split(" OR ")[0]);
                    } else if (selected[i] == 2) {
                        whereClause.add(conditions[i].split(" OR ")[1]);
                    } else {
                        whereClause.add(conditions[i].split(" OR ")[2]);
                    }
                    break;
                case 5: // categories
                    if (selected[i] == 1) {
                        whereClause.add("c.category_number= 10");
                    } else if (selected[i] == 2) {
                        whereClause.add("(c.category_number = 1 OR c.category_number = 2)");
                    } else if (selected[i] == 3) {
                        whereClause.add("(c.category_number = 6 OR c.category_number = 7)");
                    } else if (selected[i] == 4) {
                        whereClause.add("(c.category_number = 3 OR c.category_number = 4)");
                    }
                    break;
            }
        }


        List<PerfumesVO> perfumes = new ArrayList<>();
        PerfumesVO randomResult = null;
        try {
            conn = Common.getConnection();
            String sql = "SELECT DISTINCT p.* FROM " + TABLE_NAME + whereClause;
            System.out.println(sql);
            pStmt = conn.prepareStatement(sql);
            rs = pStmt.executeQuery();

            List<PerfumesVO> perfumesList = new ArrayList<>();
            while (rs.next()) {
                PerfumesVO perfumeVO = new PerfumesVO();
                perfumeVO.setPerfumeNumber(rs.getLong("perfume_Number"));
                perfumeVO.setName(rs.getString("name"));
                perfumeVO.setThumbnail(rs.getString("thumbnail"));
                perfumeVO.setBrand(rs.getInt("brand"));
                perfumesList.add(perfumeVO);

            }

            if (perfumesList.size() > 0) {
                int randomIndex = new Random().nextInt(perfumesList.size());
                randomResult = perfumesList.get(randomIndex);

                System.out.println("selected: " + randomResult);
            } else {
                System.out.println("결과 값이 없습니다.");
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {


            try {
                if (rs != null) rs.close();
                if (pStmt != null) pStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return randomResult;
    }
}
