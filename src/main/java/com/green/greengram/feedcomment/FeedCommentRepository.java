package com.green.greengram.feedcomment;

import com.green.greengram.entity.FeedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FeedCommentRepository extends JpaRepository<FeedComment, Long> {
//    @Query(value = "INSERT INTO feed_comment SET feed_id = :feedId, user_id = :userId, comment = :comment, created_at = now(), updated_at = now() ", nativeQuery = true)
//    void saveFeedComment(Long feedId, Long userId, String comment);

}
