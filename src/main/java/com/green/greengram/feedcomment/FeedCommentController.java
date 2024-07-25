package com.green.greengram.feedcomment;

import com.green.greengram.common.model.MyResponse;
import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface FeedCommentController {

//    MyResponse<Long> postFeedComment(FeedCommentPostReq p);
    void postFeedComment(HttpServletResponse res, FeedCommentPostReq p) throws IOException;

    MyResponse<List<FeedCommentGetRes>> getFeedCommentList(long feedId);

    MyResponse<Integer> deleteFeedComment(FeedCommentDeleteReq p);

    // 테스트코드 작성시 매개변수 어노테이션은 없어도 됨 (ex. @RequestBody, @ModelAttribute 등등)
}
