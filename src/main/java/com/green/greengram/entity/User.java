package com.green.greengram.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity     // 이 클래스는 entity(테이블) 이다.
public class User {

    @Id // pk
    private Long userId;        // jpa는 long 타입 pk키를 Long 래퍼클래스 사용하는 걸 권장
    private String providerType;
    private String uid;
    private String upw;
    private String nm;
    private String pic;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
