package com.hhplus.clean.lectures.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ApplyLecture {

    @Getter
    private Long id;

    @Getter
    private Long lectureId;

    @Getter
    private String userId;

    @Getter
    private LocalDateTime createdAt;

    @Builder
    public ApplyLecture(Long id, Long lectureId, String userId, LocalDateTime createdAt) {
        this.id = id;
        this.lectureId = lectureId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

}
