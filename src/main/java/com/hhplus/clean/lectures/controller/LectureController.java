package com.hhplus.clean.lectures.controller;

import com.hhplus.clean.lectures.domain.ApplyLecture;
import com.hhplus.clean.lectures.domain.Lecture;
import com.hhplus.clean.lectures.domain.LectureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureController {

    private static final Logger log = LoggerFactory.getLogger(LectureController.class);

    private LectureService lectureService;


    /**
     * 특강 선택
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<Lecture>> getLectures (){
        return null;
    }

    /**
     * 특강 신청
     */
    @PostMapping("/apply")
    public ResponseEntity<Lecture> lecture(
            @PathVariable long id,
            @RequestBody long lectureId
    ) {
        return null;
    }

    /**
     * 특강 신청 내역 조회
     */
    @GetMapping("/application/{userId}")
    public ResponseEntity<ApplyLecture> applyLectureHistory(
            @PathVariable long id
    ) {
        return null;
    }

}
