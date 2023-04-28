package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.dao.ArticleDAO;
import com.chicchic.mini_project.vo.ArticleVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ArticleController {
    // GET : 게시글
    @GetMapping("/article/{anum}")
    public ResponseEntity<List<ArticleVO>> articleList(@PathVariable("anum") int anum) {
        System.out.println(anum);
        ArticleDAO dao = new ArticleDAO();
        List<ArticleVO> list = dao.articleList(anum);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/articles/{num}")
    public ResponseEntity<List<ArticleVO>> article(@PathVariable("num") int num) {
        System.out.println(num);
        ArticleDAO dao = new ArticleDAO();
        List<ArticleVO> list = dao.article(num);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Boolean> newArticle(@RequestBody Map<String, String> regData) {
        int getBnum = Integer.parseInt(regData.get("bnum"));
        String getTitle = regData.get("title");
        String getText = regData.get("text");
        String getPwd = regData.get("pwd");
        ArticleDAO dao = new ArticleDAO();
        boolean isTrue = dao.memberRegister(getBnum, getTitle, getText, getPwd);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    @GetMapping("/articleDelete/{deleteNum}")
    public ResponseEntity<List<ArticleVO>> delete(@PathVariable("deleteNum") int deleteNum) {
        System.out.println(deleteNum);
        ArticleDAO dao = new ArticleDAO();
        List<ArticleVO> list = dao.delete(deleteNum);
        System.out.println("삭제완료");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}

