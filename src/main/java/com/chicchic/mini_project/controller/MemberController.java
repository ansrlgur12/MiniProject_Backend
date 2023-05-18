package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.dao.ArticleDAO;
import com.chicchic.mini_project.dao.MemberDAO;
import com.chicchic.mini_project.vo.ArticleVO;
import com.chicchic.mini_project.vo.MemberVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MemberController {
    // POST : 로그인
    @PostMapping("/login")
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData) {
        String id = loginData.get("id");
        String pwd = loginData.get("pwd");

        MemberDAO dao = new MemberDAO();
        boolean result = dao.loginCheck(id, pwd);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    // GET : 회원조회
    @GetMapping("/member")
    public ResponseEntity<List<MemberVO>> memberList(@RequestParam String name) {
        MemberDAO dao = new MemberDAO();
        List<MemberVO> list = dao.memberSelect();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // GET : 가입여부 확인
    @GetMapping("/check")
    public ResponseEntity<Boolean> memberCheck(@RequestParam String id) {
        MemberDAO dao = new MemberDAO();
        boolean isTrue = dao.regMemberCheck(id);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    // POST : 회원가입
    @PostMapping("/new")
    public ResponseEntity<Boolean> memberRegister(@RequestBody Map<String, String> regData) {
        String getId = regData.get("id");
        String getPwd = regData.get("pwd");
        String getName = regData.get("name");
        String getMail = regData.get("email");
        MemberDAO dao = new MemberDAO();
        boolean isTrue = dao.memberRegister(getId, getPwd, getName, getMail);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    // POST : 회원 탈퇴
    @PostMapping("/memberDelete")
    public ResponseEntity<Boolean> memberDelete(@RequestBody Map<String, String> delData) {
        String getId = delData.get("id");
        MemberDAO dao = new MemberDAO();
        boolean isTrue = dao.memberDelete(getId);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    @PostMapping("/plusThreePoint") // 3점 증가
    public ResponseEntity<Boolean> plusThreePoint(@RequestBody Map<String, String> regData) {
        String getId = regData.get("id");
        MemberDAO dao = new MemberDAO();
        boolean isTrue = dao.plusThreePoint(getId);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    @PostMapping("/plusOnePoint") // 1점 증가
    public ResponseEntity<Boolean> plusOnePoint(@RequestBody Map<String, String> regData) {
        String getId = regData.get("id");
        MemberDAO dao = new MemberDAO();
        boolean isTrue = dao.plusOnePoint(getId);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }

    @PostMapping("/myGrade") // 등급 새로고침
    public ResponseEntity<Boolean> myGrade(@RequestBody Map<String, String> regData) {
        String getId = regData.get("id");
        MemberDAO dao = new MemberDAO();
        boolean isTrue = dao.myGrade(getId);
        return new ResponseEntity<>(isTrue, HttpStatus.OK);
    }



}
