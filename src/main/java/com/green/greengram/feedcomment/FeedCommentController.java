package com.green.greengram.feedcomment;

import com.green.greengram.common.model.MyResponse;
import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentGetResInterface;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface FeedCommentController {

//    MyResponse<Long> postFeedComment(FeedCommentPostReq p);
    void postFeedComment(HttpServletResponse res, FeedCommentPostReq p) throws IOException;

//    MyResponse<List<FeedCommentGetRes>> getFeedCommentList(long feedId);

    MyResponse<List<FeedCommentGetResInterface>> getFeedCommentList(long feedId);   // jpa로 댓글 3개 이상일 경우 그 이후의 댓글 더 보기

    MyResponse<Integer> deleteFeedComment(FeedCommentDeleteReq p);

    // 테스트코드 작성시 매개변수 어노테이션은 없어도 됨 (ex. @RequestBody, @ModelAttribute 등등)
}
