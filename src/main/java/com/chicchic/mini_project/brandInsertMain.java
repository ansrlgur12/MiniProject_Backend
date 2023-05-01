package com.chicchic.mini_project;

import com.chicchic.mini_project.dao.brandInsertDAO;

public class brandInsertMain {
    public static void main(String[] args) {
        brandInsertDAO dao = new brandInsertDAO();
        dao.brandInsert();
    }
}
