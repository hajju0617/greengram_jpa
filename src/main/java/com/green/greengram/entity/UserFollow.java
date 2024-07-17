package com.green.greengram.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.annotations.Many;

@Setter
@Getter
@Entity
@Table(
        uniqueConstraints = {   // uniqueConstraints 속성은 유니크 제약 조건을 설정
                @UniqueConstraint(
                        columnNames = {"from_user_id", "to_user_id"}    // columnNames 속성은 유니크 제약 조건이 적용될 컬럼 이름들을 지정
                )
        }
)   // JPA 엔티티 클래스에 특정 컬럼 조합에 대해 유니크 제약 조건을 설정
    // from_user_id와 to_user_id의 조합이 중복되는 것을 방지
    // 유니크 제약 조건을 설정하면, 동일한 from_user_id와 to_user_id의 조합이 두 번 이상 입력되지 않도록 보장

public class UserFollow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userFollowId;

    @ManyToOne      // Many(UserFollow) To One(User)
    @JoinColumn(name = "from_user_id")  // 조인 컬럼명 (컬럼명이 됨)
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private User toUser;
}
