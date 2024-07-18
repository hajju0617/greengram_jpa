package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        uniqueConstraints = {   // uniqueConstraints 속성은 유니크 제약 조건을 설정
                @UniqueConstraint(
                        columnNames = {"user_id", "role_cd"}    // columnNames 속성은 유니크 제약 조건이 적용될 컬럼 이름들을 지정
                )
        }
)
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRolesId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_cd", nullable = false)
    private SubCode subCode;

    @Column(length = 20)
    private String role;


}
