package com.green.greengram.userfollow.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Getter
@EqualsAndHashCode
@Setter
public class UserFollowDeleteReq {

    @Schema(name="to_user_id", example = "2", description = "팔로잉 유저 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
    private long toUserId;

    @ConstructorProperties({ "to_user_id" })    // 생성자를 통해 주입
    public UserFollowDeleteReq(long toUserId) {
        this.toUserId = toUserId;
    }
}
