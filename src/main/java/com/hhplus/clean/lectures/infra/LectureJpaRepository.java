package com.hhplus.clean.lectures.infra;

import com.hhplus.clean.lectures.infra.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {

}
