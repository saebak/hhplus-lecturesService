package com.hhplus.clean.lectures.controller.mapper;

import com.hhplus.clean.lectures.domain.LectureInfo;
import com.hhplus.clean.lectures.infra.entity.LectureInfoEntity;

public class LectureInfoMapper {

    /**
     * Entity에서 domain으로 변환
     * @param entity
     * @return
     */
    public static LectureInfo toDomain(LectureInfoEntity entity) {
        return LectureInfo.builder()
                .id(entity.getId())
                .lectureId(entity.getLectureId())
                .instructor(entity.getInstructor())
                .limitCount(entity.getLimitCount())
                .openDate(entity.getOpenDate())
                .build();
    }


    /**
     * domain에서 entity로 변환
     * @param domain
     * @return
     */
    public static LectureInfoEntity toEntity(LectureInfo domain) {
        LectureInfoEntity entity = new LectureInfoEntity();
        entity.setId(domain.getId());
        entity.setLectureId(domain.getLectureId());
        entity.setInstructor(domain.getInstructor());
        entity.setLimitCount(domain.getLimitCount());
        entity.setOpenDate(domain.getOpenDate());
        return entity;
    }

}
