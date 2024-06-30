package com.hhplus.clean.lectures.controller;

import com.hhplus.clean.lectures.controller.dto.SaveLectureInput;
import com.hhplus.clean.lectures.domain.ApplyLecture;
import com.hhplus.clean.lectures.domain.Lecture;
import com.hhplus.clean.lectures.domain.LectureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureController {

    private static final Logger log = LoggerFactory.getLogger(LectureController.class);

    @Autowired
    private LectureService lectureService;

    /**
     * 특강목록 조회
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<Lecture>> getLectures (){
        List<Lecture> lectures = lectureService.findLectures();
        return ResponseEntity.ok().body(lectures);
    }

    /**
     * 특강 신청
     */
    @PostMapping("/apply")
    public ResponseEntity<Lecture> lecture(
            @RequestBody SaveLectureInput input
    ) {
        long applyResult;
        try {
            applyResult = lectureService.registerLecture(input);
            if (applyResult <= 0) {
                return ResponseEntity.status(500).build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok().build();
    }

    /**
     *  특강 신청 완료 여부 조회
     */
    @GetMapping("/application/{userId}")
    public ResponseEntity<Boolean> applyLectureHistory(
            @PathVariable long userId,
            @RequestParam SaveLectureInput input
    ) {
        boolean applyStatus = lectureService.getApplyStatusByUserId(input);
        return ResponseEntity.ok().body(applyStatus);
    }

}
