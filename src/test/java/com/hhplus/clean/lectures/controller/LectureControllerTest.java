package com.hhplus.clean.lectures.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.clean.lectures.controller.dto.SaveLectureInput;
import com.hhplus.clean.lectures.domain.LectureRepository;
import com.hhplus.clean.lectures.domain.LectureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LectureControllerTest {

    MockMvc mockMvc;

    @Autowired
    LectureService lectureService;

    @Autowired
    LectureRepository lectureRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    @DisplayName("특강 목록 조회 테스트")
    void getlecturesTest() throws Exception {
        this.mockMvc
                .perform(get("/lectures"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("특강 신청 테스트")
    void saveLectureTest() throws Exception {
        this.mockMvc
                .perform(post("/lectures/apply"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("특강 신청 여부 조회 테스트")
    void applyLectureHistoryTest() throws Exception {
        this.mockMvc
                .perform(get("/application/1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
