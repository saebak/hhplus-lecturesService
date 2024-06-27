package com.hhplus.clean.lectures.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@DisplayName("특강 조회 및 신청 테스트")
public class LectureServiceTest {

    @InjectMocks
    private LectureService lectureService;
    @Mock
    private LectureRepository lectureRepository;
    private Lecture basicLecture;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        long lectureId = 1;
        String title = "테스트 특강";
        int limitCount = 30;
        LocalDateTime openDate = LocalDateTime.now();
        basicLecture = new Lecture().builder()
                .id(lectureId)
                .title(title)
                .limitCount(limitCount)
                .openDate(openDate)
                .build();
    }

    @Test
    @DisplayName("해당 ID의 특강을 조회할 때 존재하는 경우 테스트")
    public void getLectureByIdTest() {
        // given
        long lectureId = 1;
        given(lectureRepository.findLectureById(anyLong())).willReturn(basicLecture);

        // when
        Lecture result = lectureService.findLectureById(lectureId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(lectureId);
    }

    @Test
    @DisplayName("해당 ID의 특강을 조회할 때 존재하지 않는 경우 테스트")
    public void getLectureByIdNullTest() {
        // given
        long lectureId = 1;
        given(lectureRepository.findLectureById(anyLong())).willReturn(null);

        // when
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(()->{
                    lectureService.findLectureById(lectureId);
                }).withMessage("This lecture is not exist");
    }

    @Test
    @DisplayName("특정 userId로 선차순으로 제공되는 특강을 신청하는 기능 테스트")
    public void registerLectureTest() {
        // given
        long lectureId = 1;
        String userId = "park.saemi";
        int limitCount = 30;

        given(lectureRepository.findLectureById(anyLong())).willReturn(basicLecture);
        //given(lectureRepository.saveApplyLecture())
    }

}
