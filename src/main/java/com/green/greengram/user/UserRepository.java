package com.green.greengram.user;

import com.green.greengram.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
                                                 // <Entity, Pk 타입>

}
