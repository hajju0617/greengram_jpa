package com.green.greengram.feedcomment;

import com.green.greengram.entity.Feed;
import com.green.greengram.entity.FeedComment;
import com.green.greengram.entity.User;
import com.green.greengram.exception.CustomException;
import com.green.greengram.exception.MemberErrorCode;
import com.green.greengram.feed.FeedRepository;
import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import com.green.greengram.security.AuthenticationFacade;
import com.green.greengram.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeedCommentServiceImpl implements FeedCommentService {
    private final FeedCommentMapper mapper;
    private final AuthenticationFacade authenticationFacade;
    private final FeedCommentRepository repository;
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;

    public long postFeedComment(FeedCommentPostReq p) {
        //Feed Entity(영속성)
        Feed feed = feedRepository.getReferenceById(p.getFeedId());

        //User Entity(영속성)
        User user = userRepository.getReferenceById(authenticationFacade.getLoginUserId());

        //Feed Comment Entity (비영속성)
        FeedComment fc = new FeedComment();
        fc.setFeed(feed);
        fc.setUser(user);
        fc.setComment(p.getComment());

        repository.save(fc);

//        FeedComment fc2 = repository.save(fc);
//        log.info("fc equals fc2 : {}", fc == fc2);      // FeedComment fc2 = repository.save(fc); 할 필요x WHY? -> fc와 fc2의 주소값은 같다 (log로 확인가능)
        return fc.getFeedCommentId();

//        p.setUserId(authenticationFacade.getLoginUserId());
//        int affectedRows = mapper.postFeedComment(p);
//        return p.getFeedCommentId();
    }

    public int deleteFeedComment(FeedCommentDeleteReq p) {
        FeedComment fc = repository.getReferenceById(p.getFeedCommentId());
        if (fc.getUser().getUserId() != authenticationFacade.getLoginUserId()) {    // fc.getUser().getUserId() : 그래프 탐색
            throw new CustomException(MemberErrorCode.UNAUTHORIZED);
        }
        repository.delete(fc);
        return 1;

//        p.setSignedUserId(authenticationFacade.getLoginUserId());
//        return mapper.deleteFeedComment(p);
    }

    public List<FeedCommentGetRes> getFeedComment(long feedId) {
        return mapper.selFeedCommentList(feedId);
    }
}
