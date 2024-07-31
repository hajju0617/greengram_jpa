package com.green.greengram.feedcomment;

import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedComment;
import com.green.greengram.feedcomment.model.FeedCommentGetResInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedCommentRepository extends JpaRepository<FeedComment, Long> {
//    @Query(value = "INSERT INTO feed_comment SET feed_id = :feedId, user_id = :userId, comment = :comment, created_at = now(), updated_at = now() ", nativeQuery = true)
//    void saveFeedComment(Long feedId, Long userId, String comment);

    List<FeedComment> findAllFeedCommentByFeedOrderByFeedCommentId(Feed feed);  // 모든 댓글 다 불러옴
    // ASC는 생략되어 있음, All 이므로 리스트형태로 넘어옴, By 라서 WHERE 절

    @Query(value = " SELECT fc.feed_comment_id as feedCommentId, fc.comment, fc.created_at as createdAt" +
            " , u.user_id as writerId, u.nm as writerNm, u.pic as writerPic  " +
            " FROM feed_comment fc " +
            " INNER JOIN feed f ON fc.feed_id = f.feed_id " +
            " INNER JOIN user u ON fc.user_id = u.user_id " +
            " WHERE f.feed_id = :feedId " +
            " ORDER BY fc.feed_comment_id ASC" +
            " LIMIT 3, 9999 ", nativeQuery = true)
    List<FeedCommentGetResInterface> findAllByFeedCommentLimit3AndInfinity(Long feedId);
}
