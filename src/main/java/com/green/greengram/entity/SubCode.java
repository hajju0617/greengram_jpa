package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(
        uniqueConstraints = {   // uniqueConstraints 속성은 유니크 제약 조건을 설정
                @UniqueConstraint(
                        columnNames = {"main_code_id", "val"}    // columnNames 속성은 유니크 제약 조건이 적용될 컬럼 이름들을 지정
                )
        }
)
public class SubCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCodeId;

    @ManyToOne
    @JoinColumn(name = "main_code_id", nullable = false)
    private MainCode mainCode;

    @Column(length = 30, nullable = false)
    private String val;

    @Column(length = 30)
    private String description;
}
