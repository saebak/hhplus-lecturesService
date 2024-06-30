package com.hhplus.clean.lectures.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaveLectureInput {
    private long lectureId;
    private String userId;
}
