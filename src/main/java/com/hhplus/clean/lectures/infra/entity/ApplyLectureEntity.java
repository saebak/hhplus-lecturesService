package com.hhplus.clean.lectures.infra.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="APPLY_LECTURE")
public class ApplyLectureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="LECTURE_ID")
    private Long lectureId;

    @Column(name="USER_ID")
    private String userId;

    @Column(name="CREATE_AT")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name="UPDATE_AT")
    @LastModifiedDate
    private LocalDateTime updateAt;
}
