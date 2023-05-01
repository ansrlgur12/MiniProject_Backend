package com.chicchic.mini_project;

import com.chicchic.mini_project.dao.noteInsertDAO;

public class noteInsertMain {
    public static void main(String[] args) {

        noteInsertDAO dao = new noteInsertDAO();
        dao.noteInsert();
    }
}
