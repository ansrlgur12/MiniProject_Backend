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

    @GetMapping("/smallArticle/{anum}") // 작은 게시글목록
    public ResponseEntity<List<ArticleVO>> smallArticleList(@PathVariable("anum") int anum) {
        System.out.println(anum + "카테고리");
        ArticleDAO dao = new ArticleDAO();
        List<ArticleVO> list = dao.articleList(anum);
        System.out.println(list);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/articles/{num}")
    public ResponseEntity<List<ArticleVO>> article(@PathVariable("num") int num) {
        System.out.println(num);
        ArticleDAO dao = new ArticleDAO();
        List<ArticleVO> list = dao.article(num);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/newArticle") // 글작성
    public ResponseEntity<Boolean> newArticle(@RequestBody Map<String, String> regData) {
        int getBnum = Integer.parseInt(regData.get("bnum"));
        String getTitle = regData.get("title");
        String getText = regData.get("text");
        String getPwd = regData.get("pwd");
        ArticleDAO dao = new ArticleDAO();
        boolean isTrue = dao.newArticle(getBnum, getTitle, getText, getPwd);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    @GetMapping("/articleDelete/{anum}") // 글삭제
    public ResponseEntity<List<ArticleVO>> deleteArticle(@PathVariable("anum") int anum) {
        ArticleDAO dao = new ArticleDAO();
        dao.delete(anum);
        System.out.println(anum);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update") // 글 수정
    public ResponseEntity<Boolean> updateArticle(@RequestBody Map<String, String> regData) {
        int getAnum = Integer.parseInt(regData.get("anum"));
        int getBnum = Integer.parseInt(regData.get("bnum"));
        String getTitle = regData.get("title");
        String getText = regData.get("text");
        String getPwd = regData.get("pwd");
        ArticleDAO dao = new ArticleDAO();
        boolean isTrue = dao.update(getAnum, getBnum, getTitle, getText, getPwd);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }
}

