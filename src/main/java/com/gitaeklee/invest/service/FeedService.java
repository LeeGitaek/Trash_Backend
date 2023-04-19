package com.gitaeklee.invest.service;

import com.gitaeklee.invest.domain.entity.Feed;
import com.gitaeklee.invest.domain.entity.FeedThread;
import com.gitaeklee.invest.domain.entity.Like;
import com.gitaeklee.invest.domain.entity.Tag;
import com.gitaeklee.invest.repository.repo.FeedRepository;
import com.gitaeklee.invest.repository.repo.FeedThreadRepository;
import com.gitaeklee.invest.repository.repo.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FeedService {

    private final LikeRepository likeRepository;
    private final FeedThreadRepository feedThreadRepository;
    private final FeedRepository feedRepository;

    // feed, feed thread, feed like

    /* find feed byId */
    public Feed findFeedById(Long id) {
        return feedRepository.findFeedById(id);
    }

    /* post feed */
    @Transactional
    public Long saveFeed(Feed feed) {
        // Validate the feed text
        System.out.println("feed id param : " + feed.getId());
        if (feed.getFeedText().length() == 0) {
            throw new IllegalStateException("Error: empty feed text");
        }

        //validateFeed(feed.getId());

        // Save the feed
        feedRepository.saveFeed(feed);
        return feed.getId();
    }

    /* validate empty text in feed */
    private void validateFeed(Long id) {
        System.out.println("feed id valid : " + id);
        if(findFeedById(id) != null) {
            throw new IllegalStateException("Duplicate: duplicate feed id");
        }
    }

    public List<Feed> findAll() {
        return feedRepository.findAll();
    }

    /* search feed */
    public List<Feed> searchFeedByKeyword(String keyword) {
        return feedRepository.findFeedByKeyword(keyword);
    }

    /* search feed by any ( user, tag, feed text ) */
    public List<Feed> searchFeedByAny(String q) {
        return feedRepository.findFeedByAny(q);
    }

    /* update text in feed */
    @Transactional
    public void updateFeedText(Long feedId, String feedText) {
        Feed feed = feedRepository.findFeedById(feedId);
        feed.setFeedText(feedText);
    }

    /* update tag in feed */
    @Transactional
    public void updateFeedTag(Long feedId, Tag tag) {
        Feed feed = feedRepository.findFeedById(feedId);
        feed.setTag(tag);
    }

    /* feed thread */
    @Transactional
    public Long saveFeedThread(FeedThread feedThread) {
        feedThreadRepository.saveThread(feedThread);
        return feedThread.getId();
    }

    /* validate empty thread */
    private void validateThread(FeedThread feedThread) {
        if(feedThread.getThreadText().length() == 0) {
            throw new IllegalStateException("error : empty thread text");
        }
    }

    /* find thread by id */
    public FeedThread findThreadById(Long threadId) {
        return feedThreadRepository.findThreadOne(threadId);
    }

    /* update thread */
    @Transactional
    public void updateThreadText(Long threadId, String threadText) {
        FeedThread feedThread = feedThreadRepository.findThreadOne(threadId);
        feedThread.setThreadText(threadText);
    }

    /* feed like */
    @Transactional
    public Long saveFeedLike(Like like) {
        validateLikeAction(like);
        likeRepository.saveLike(like);
        return like.getId();
    }

    /* validate like action */
    private void validateLikeAction(Like like) {
        Feed feed = feedRepository.findFeedById(like.getFeed().getId());
        if(feed == null) {
            throw new IllegalStateException("error : not exist feed.");
        }


    }

    @Transactional
    public Long deleteLike(Like like) {
        return likeRepository.deleteLike(like.getUser().getId(), like.getFeed().getId());
    }

    /// ....
}
