package com.green.greengram.feedfavorite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.beans.ConstructorProperties;

@EqualsAndHashCode
@Getter
@ToString
public class FeedFavoriteToggleReq {

    @Schema(name = "feed_id")
    private long feedId; //feed_id

    @JsonIgnore
    private long userId; //user_id

    @ConstructorProperties({" feed_id "})
    public FeedFavoriteToggleReq(long feedId) {
        this.feedId = feedId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
