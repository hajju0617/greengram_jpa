package com.green.greengram.user;

import com.green.greengram.entity.User;
import com.green.greengram.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    // 쿼리 메서드 select * from user_role WHERE user_id = #{user_id}
    List<UserRole> findAllByUser(User user);                  // findAll : List 반환
                                                              // 필드명을 작성해야 함 -> User : 필드명
}
