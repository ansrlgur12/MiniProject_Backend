package com.chicchic.mini_project;

import com.chicchic.mini_project.dao.DataInsertDAO;

import java.io.IOException;



public class perfumeinsertMain {
    public static  void main(String[] args) throws IOException {


        DataInsertDAO dao = new DataInsertDAO();
        dao.PerfumeInsert();
    }
    }






