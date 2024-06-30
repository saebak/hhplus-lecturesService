package com.hhplus.clean.lectures.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Lecture {

    @Getter
    private Long id;

    @Getter
    private String title;

    @Getter
    private int limitCount;

    @Getter
    private LocalDateTime openDate;

    @Builder
    public Lecture(Long id, String title, int limitCount, LocalDateTime openDate) {
        this.id = id;
        this.title = title;
        this.limitCount = limitCount;
        this.openDate = openDate;
    }

}
