package com.chicchic.mini_project.controller;

import com.chicchic.mini_project.dao.NoteFinderDAO;
import com.chicchic.mini_project.vo.PerfumeVO;
import com.chicchic.mini_project.vo.PerfumesVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class  NoteFinderController {
@GetMapping("/NoteFinderResult/{ids}")


    public ResponseEntity<List<PerfumesVO>> NoteFinderResult(@PathVariable("ids")String[] ids){
    NoteFinderDAO dao = new NoteFinderDAO();
    List<PerfumesVO> perfumes = dao.NoteFinderResult(ids);
    System.out.println(perfumes);
    return new ResponseEntity<List<PerfumesVO>>(perfumes, HttpStatus.OK);
    }

}
