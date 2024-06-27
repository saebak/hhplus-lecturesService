package com.hhplus.clean.lectures.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LectureInfo {

    @Getter
    private Long id;

    @Getter
    private Long lectureId;

    @Getter
    private String instructor;

    @Getter
    private int limitCount;

    @Getter
    private LocalDateTime openDate;

    @Builder
    public LectureInfo(Long id, Long lectureId, String instructor, int limitCount, LocalDateTime openDate) {
        this.id = id;
        this.lectureId = lectureId;
        this.instructor = instructor;
        this.limitCount = limitCount;
        this.openDate = openDate;
    }

}
