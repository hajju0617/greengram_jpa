package com.green.greengram.entity;

import com.green.greengram.security.SignInProviderType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity     // 이 클래스는 entity(테이블) 이다.
public class User extends UpdatedAt {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;        // jpa는 long 타입 pk키를 Long 래퍼클래스 사용하는 걸 권장

    @ColumnDefault("4")
    @Column(nullable = false)   // not null
    private SignInProviderType providerType;

    @Column(length = 50, nullable = false)  // 문자열 길이, not null
    private String uid;

    @Column(length = 100, nullable = false)
    private String upw;

    @Column(length = 50, nullable = false)
    private String nm;

    @Column(length = 200)
    private String pic;





}
