package com.hhplus.clean.lectures.domain;

import com.hhplus.clean.lectures.controller.dto.SaveLectureInput;

import java.util.List;

public interface LectureRepository {

    Lecture findLectureById(Long lectureId);

    List<ApplyLecture> findApplyLectureListByLectureId(Long lectureId);

    ApplyLecture findApplyLetureByLectureIdAndUserId(SaveLectureInput input);

    Long saveApplyLecture(ApplyLecture applyLecture);

}
