package com.hhplus.clean.lectures.controller.mapper;

import com.hhplus.clean.lectures.domain.ApplyLecture;
import com.hhplus.clean.lectures.infra.entity.ApplyLectureEntity;

public class ApplyLectureMapper {

    /**
     * Entity에서 domain으로 변환
     * @param entity
     * @return
     */
    public static ApplyLecture toDomain(ApplyLectureEntity entity) {
        return ApplyLecture.builder()
                .id(entity.getId())
                .lectureId(entity.getLectureId())
                .userId(entity.getUserId())
                .createdAt(entity.getCreatedAt())
                .build();
    }


    /**
     * domain에서 entity로 변환
     * @param domain
     * @return
     */
    public static ApplyLectureEntity toEntity(ApplyLecture domain) {
        ApplyLectureEntity entity = new ApplyLectureEntity();
        entity.setId(domain.getId());
        entity.setLectureId(domain.getLectureId());
        entity.setUserId(domain.getUserId());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }

}
