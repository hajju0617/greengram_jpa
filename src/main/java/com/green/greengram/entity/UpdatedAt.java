package com.green.greengram.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class UpdatedAt extends CreatedAt {

    @LastModifiedDate   // Spring Data JPA에서 제공하는 애너테이션, 엔티티가 마지막으로 수정된 날짜와 시간을 자동으로 기록하기 위해 사용
                        // 주로 감사(auditing) 기능의 일부로 사용되고 엔티티가 업데이트될 때마다 해당 필드를 자동으로 갱신
    private LocalDateTime updatedAt;
}
