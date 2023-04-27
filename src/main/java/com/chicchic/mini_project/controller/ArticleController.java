package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.dao.ArticleDAO;
import com.chicchic.mini_project.vo.ArticleVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ArticleController {
    // GET : 게시글
    @GetMapping("/article/{anum}")
    public ResponseEntity<List<ArticleVO>> memberList(@PathVariable("anum") String anum) {
        ArticleDAO dao = new ArticleDAO();
        List<ArticleVO> list = dao.article();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

