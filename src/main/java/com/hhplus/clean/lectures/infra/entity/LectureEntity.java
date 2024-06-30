package com.hhplus.clean.lectures.infra.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="LECTURES")
public class LectureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="LIMIT_COUNT")
    private int limitCount;

    @Column(name="OPEN_DATE")
    private LocalDateTime openDate;

    @Column(name="CREATE_AT")
    @CreatedDate
    private LocalDateTime createdAt;
}
