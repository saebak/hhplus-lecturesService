package com.hhplus.clean.lectures.domain;

import com.hhplus.clean.lectures.controller.dto.SaveLectureInput;
import com.hhplus.clean.lectures.infra.LectureRepositoryImpl;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@DisplayName("특강 조회 및 신청 테스트")
public class LectureServiceIntergrationTest {

    private LectureService lectureService;

    @Autowired
    private LectureRepository lectureRepository;

    @BeforeEach
    public void setUp() {
        this.lectureService = new LectureService(lectureRepository);
    }

    // 동시성 제어
    @Test
    @DisplayName("여러 스레드가 특강 신청시 동시성 제어 테스트")
    public void registerLectureIntegrationTest() throws InterruptedException {
        // given
        int threadCnt = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCnt);  // 고정된 스레드 풀을 생성하여 여러 스레드에서 작업을 실행
        CountDownLatch latch = new CountDownLatch(threadCnt);                       // 모든 스레드가 작업 완료할때 까지 대기
        long lectureId = 1;
        AtomicInteger failCnt = new AtomicInteger();

        for (int i=1; i<=threadCnt; i++) {
            long finalI = i;
            SaveLectureInput input = new SaveLectureInput(lectureId, "park.saemi0" + i);
            executorService.submit(() -> {
                try {
                    try {
                        Long result = lectureService.registerLecture(input);
                    } catch (Exception e) {
                        failCnt.getAndIncrement();
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();                  // 모든 thread가 종료될대까지 기다림
        executorService.shutdown();     // thread풀 종료

        Lecture lecture = lectureService.findLectureById(lectureId);
        List<ApplyLecture> applyResult = lectureService.findApplyLectureList(lectureId);

        // then
        assertThat(applyResult).isNotNull();
        assertThat(applyResult.size()).isLessThanOrEqualTo(lecture.getLimitCount());
        assertThat(failCnt).isEqualTo(20);
    }

}
