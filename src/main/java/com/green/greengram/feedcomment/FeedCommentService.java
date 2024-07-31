package com.green.greengram.feedcomment;

import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentGetResInterface;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;

import java.util.List;

public interface FeedCommentService {

    long postFeedComment(FeedCommentPostReq p);

    int deleteFeedComment(FeedCommentDeleteReq p);

//    List<FeedCommentGetRes> getFeedComment(long feedId);

    List<FeedCommentGetResInterface> feedCommentListGet(long feedId);   // jpa로 댓글 3개 이상일 경우 그 이후의 댓글 더 보기
}
