package com.green.greengram.feedcomment.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class FeedCommentGetRes {
    private Long feedCommentId;
    private String comment;
    private String createdAt;
    private Long writerId;
    private String writerNm;
    private String writerPic;
}
