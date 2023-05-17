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
    private final String TABLE_NAME = "perfumes";

    public PerfumesVO getPerfumesByConditions(Integer[] selected) {
        StringJoiner whereClause = new StringJoiner(" AND ", " WHERE ", "");


        String[] conditions = {
                "seasons LIKE '%1%' OR seasons LIKE '%2%' OR seasons LIKE '%3%' OR seasons LIKE '%4%'",
                "(categories  LIKE '%1%' OR  categories LIKE '%2%' OR  categories  LIKE '%3%' OR  categories  OR '%4%' OR categories  LIKE '%5%') OR (categories  LIKE '%6%' OR  categories  LIKE '%7%' OR  categories  LIKE '%8%' OR  categories  LIKE '%9%' OR  categories  LIKE '%10%')",
                "brand IN (80,1871,1328,1506,1517,1525,1543,1623,1631,1708,921,1718,1724,1816,1934,1977,2104,2208,2415,2706,1054,315, 749, 614,647, 1745, 855, 555, 653,534, 524,528, 3032,3130,3140,3227) OR brand NOT IN (80,1871,1328,1506,1517,1525,1543,1623,1631,1708,921,1718,1724,1816,1934,1977,2104,2208,2415,2706,1054,315, 749, 614,647, 1745, 855, 555, 653,534, 524,528, 3032,3130,3140,3227)",
                "base_notes IS NOT NULL OR base_notes IS NULL",
                "gender = 1 OR gender = 0 OR gender  IS NOT NULL",
                "categories LIKE '%10%' OR (categories LIKE '%1%' OR categories LIKE '%2%') OR (categories LIKE '%6%' OR categories LIKE '%7%') OR (categories LIKE '%3%' OR categories LIKE '%4%')"
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
                        whereClause.add("(categories  LIKE '%1%' OR  categories LIKE '%2%' OR  categories  LIKE '%3%' OR  categories  LIKE '%4%' OR categories  LIKE '%5%')");

                    } else if (selected[i] == 2) {
                        whereClause.add("(categories  LIKE '%6%' OR  categories  LIKE '%7%' OR  categories  LIKE '%8%' OR  categories  LIKE '%9%' OR  categories  LIKE '%10%')");

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
                        whereClause.add("categories LIKE '%10%'");
                    } else if (selected[i] == 2) {
                        whereClause.add("(categories LIKE '%1%' OR categories LIKE '%2%')");
                    } else if (selected[i] == 3) {
                        whereClause.add("(categories LIKE '%6%' OR categories LIKE '%7%')");
                    } else if (selected[i] == 4) {
                        whereClause.add("(categories LIKE '%3%' OR categories LIKE '%4%')");
                    }
                    break;
            }
        }


        List<PerfumesVO> perfumes = new ArrayList<>();
        PerfumesVO randomResult = null;
        try {
            conn = Common.getConnection();
            String sql = "SELECT * FROM " + TABLE_NAME + whereClause;
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
