package com.hhplus.clean.lectures.controller.mapper;

import com.hhplus.clean.lectures.domain.Lecture;
import com.hhplus.clean.lectures.infra.entity.LectureEntity;

public class LectureMapper {

    /**
     * Entity에서 domain으로 변환
     * @param entity
     * @return
     */
    public static Lecture toDomain(LectureEntity entity) {
        return Lecture.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .limitCount(entity.getLimitCount())
                .openDate(entity.getOpenDate())
                .build();
    }


    /**
     * domain에서 entity로 변환
     * @param domain
     * @return
     */
    public static LectureEntity toEntity(Lecture domain) {
        LectureEntity entity = new LectureEntity();
        entity.setId(domain.getId());
        entity.setTitle(domain.getTitle());
        entity.setLimitCount(domain.getLimitCount());
        entity.setOpenDate(domain.getOpenDate());
        return entity;
    }

}
