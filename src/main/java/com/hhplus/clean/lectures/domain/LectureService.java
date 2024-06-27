package com.hhplus.clean.lectures.domain;

import com.hhplus.clean.common.exception.AlreadyApplyLectureException;
import com.hhplus.clean.common.exception.LectureApplyLimitFullException;
import com.hhplus.clean.lectures.controller.dto.SaveLectureInput;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LectureService {
    private LectureRepository lectureRepository;

    public LectureService (LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    /**
     * 특강 목록 조회
     * @return
     */
    public List<Lecture> findLectures() {
        List<Lecture> lectures = lectureRepository.findLectures();
        return lectures;
    }

    /**
     * 해당 id의 특강 신청 내역 조회
     * @param lectureId
     * @return
     */
    public List<ApplyLecture> findApplyLectureList(Long lectureId) {
        Lecture lecture = findLectureById(lectureId);
        return lectureRepository.findApplyLectureListByLectureId(lecture.getId());
    }

    /**
     * 특강 신청
     * @param input
     * @return
     * @throws Exception
     */
    public Long registerLecture(SaveLectureInput input) throws Exception {
        Lecture lecture = findLectureById(input.getLectureId());
        Long result = 0L;
            // 인원수 체크 추가
            List<ApplyLecture> applyLectures = findApplyLectureList(input.getLectureId());
            if (applyLectures.size() >=lecture.getLimitCount()) {
                throw new LectureApplyLimitFullException("신청 정원이 초과되었습니다.");
            }

            // 이미 신청한 유저인지 체크
            ApplyLecture applyUser = applyLectures.stream()
                    .filter(user -> user.getUserId() == input.getUserId())
                    .findAny().orElse(null);
            if (applyUser != null) {
                throw new AlreadyApplyLectureException("이미 신청한 특강입니다.");
            }
            
            // 같은 강의의 같은날짜에 신청한 기록이 있는지 체크 (리팩토링 예정..)


            ApplyLecture applyLecture = new ApplyLecture();
            applyLecture.setLectureId(input.getLectureId());
            applyLecture.setUserId(input.getUserId());
            result = lectureRepository.saveApplyLecture(applyLecture);

        return result;
    }

    /**
     * 특강 신청 여부 조회 - 신청완료 true / 미신청 false
     * @param input
     * @return
     */
    public boolean getApplyStatusByUserId(SaveLectureInput input) {
        ApplyLecture applyStatus = lectureRepository.findApplyLetureByLectureIdAndUserId(input);
        return applyStatus == null ? false : true;
    }
    
    // 해당 id의 특강 조회
    public Lecture findLectureById(Long lectureId) {
        Lecture lecture = lectureRepository.findLectureById(lectureId);
        if (lecture == null) {
            throw new NullPointerException("This lecture is not exist");
        }
        return lecture;
    }

}
