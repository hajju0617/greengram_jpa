package com.green.greengram.user;

import com.green.greengram.entity.User;
import com.green.greengram.security.SignInProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
                                                 // <Entity, Pk 타입>

    // 쿼리 메서드
    User getUserByProviderTypeAndUid(SignInProviderType providerType, String uid);      // find or get => select 의미
    // = select * from user WHERE provider_type = #{providerType} and uid = #{uid}

}
