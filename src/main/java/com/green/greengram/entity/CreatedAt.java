package com.green.greengram.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass   // 부모클래스랑 맵핑 가능하게 해줌
@EntityListeners(AuditingEntityListener.class)  //JPA 엔티티 클래스에 적용되는 애너테이션
                                                // 엔티티의 생명주기 이벤트를 감지하고 감사(auditing) 기능을 제공하기 위해 사용됨.
                                                // 주로 데이터의 생성 및 수정 시간을 자동으로 기록하는 데 사용
public class CreatedAt {


    @Column(nullable = false)
    @CreatedDate    // JPA가 insert때 현재 일시 값을 주입, default current_timestamp() 속성을 추가하는 것이 아님
    private LocalDateTime createdAt;


}
