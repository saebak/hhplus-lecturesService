package com.hhplus.clean.lectures.infra;


import com.hhplus.clean.lectures.controller.dto.SaveLectureInput;
import com.hhplus.clean.lectures.controller.mapper.ApplyLectureMapper;
import com.hhplus.clean.lectures.controller.mapper.LectureMapper;
import com.hhplus.clean.lectures.domain.ApplyLecture;
import com.hhplus.clean.lectures.domain.Lecture;
import com.hhplus.clean.lectures.domain.LectureRepository;
import com.hhplus.clean.lectures.infra.entity.ApplyLectureEntity;
import com.hhplus.clean.lectures.infra.entity.LectureEntity;
import com.hhplus.clean.lectures.infra.entity.QApplyLectureEntity;
import com.hhplus.clean.lectures.infra.entity.QLectureEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LectureRepositoryImpl implements LectureRepository {

    private LectureJpaRepository jpaRepository;
    private final JPAQueryFactory query;

    QLectureEntity lectureEntity = QLectureEntity.lectureEntity;
    QApplyLectureEntity  applyLectureEntity = QApplyLectureEntity.applyLectureEntity;

    public LectureRepositoryImpl(LectureJpaRepository jpaRepository, JPAQueryFactory query) {
        this.jpaRepository = jpaRepository;
        this.query = query;
    }

    /**
     * 특강 목록 조회
     * @return
     */
    public List<Lecture> findLectures() {
        List<LectureEntity> entityList = jpaRepository.findAll();

        List<Lecture> lectures = new ArrayList<Lecture>();
        for(LectureEntity entity : entityList) {
            lectures.add(LectureMapper.toDomain(entity));
        }
        return lectures;
    }

    /**
     * 해당 id의 특강 조회
     * @param lectureId
     * @return
     */
    @Override
    public Lecture findLectureById(Long lectureId) {
        LectureEntity entity = query.selectFrom(lectureEntity)
                .where(lectureEntity.id.eq(lectureId))
                .fetchOne();

        return LectureMapper.toDomain(entity);
    }

    /**
     * 해당 id의 특강 신청 내역 조회
     * @param lectureId
     * @return
     */
    @Override
    public List<ApplyLecture> findApplyLectureListByLectureId(Long lectureId) {
        List<ApplyLectureEntity> entityList = query.selectFrom(applyLectureEntity)
                .where(applyLectureEntity.lectureId.eq(lectureId))
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)        // 비관적 락걸기
                .fetch();

        List<ApplyLecture> applyLectures = new ArrayList<ApplyLecture>();
        for(ApplyLectureEntity entity : entityList) {
            applyLectures.add(ApplyLectureMapper.toDomain(entity));
        }
        return applyLectures;
    }

    /**
     * 특정 유저가 해당 id 특강을 신청 여부 조회
     * @param input
     * @return
     */
    @Override
    public ApplyLecture findApplyLetureByLectureIdAndUserId(SaveLectureInput input) {
        ApplyLectureEntity entity = query.selectFrom(applyLectureEntity)
                .where(applyLectureEntity.lectureId.eq(input.getLectureId()),
                        applyLectureEntity.userId.eq(input.getUserId()))
                .fetchOne();

        return ApplyLectureMapper.toDomain(entity);
    }

    /**
     * 특강 신청/내역 저장
     * @param applyLecture
     * @return
     */
    @Override
    public Long saveApplyLecture(ApplyLecture applyLecture) {
        Long result = query.insert(applyLectureEntity)
                .set(applyLectureEntity.lectureId, applyLecture.getLectureId())
                .set(applyLectureEntity.userId, applyLecture.getUserId())
                .setLockMode(LockModeType.PESSIMISTIC_READ)     // 비관적 락걸기
                .execute();
        return result;
    }
}
