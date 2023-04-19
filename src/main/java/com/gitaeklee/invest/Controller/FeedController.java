package com.gitaeklee.invest.Controller;

import com.gitaeklee.invest.domain.entity.*;
import com.gitaeklee.invest.repository.dto.FeedDto;
import com.gitaeklee.invest.repository.dto.FeedThreadDto;
import com.gitaeklee.invest.repository.dto.request.LikeRequest;
import com.gitaeklee.invest.repository.dto.request.PostRequest;
import com.gitaeklee.invest.repository.dto.request.ThreadRequest;
import com.gitaeklee.invest.service.FeedService;
import com.gitaeklee.invest.service.TagService;
import com.gitaeklee.invest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;
    private final UserService userService;
    private final TagService tagService;

    @PostMapping("/api/v2/feed/post")
    public FeedResponse postFeed(@RequestBody @Validated PostRequest request) {

        User user = userService.findOne(request.getUserId());
        Tag tag = tagService.findTagByName(request.getTagName());

        Feed newFeed = new Feed();
        newFeed.setUser(user);
        newFeed.setTag(tag);
        newFeed.setFeedTag(tag);
        newFeed.setFeedText(request.getFeedText());
        newFeed.setLikeCount();
        newFeed.setViewCount();
        newFeed.setThreadCount();
        newFeed.setFeedToken("sdfhwkjdhw"+request.getFeedText());
        newFeed.setDatetime();

        System.out.println("new feed id : " + newFeed.getId());

        Long feedId = feedService.saveFeed(newFeed);
        return new FeedResponse(feedId);
    }

    @Data
    @AllArgsConstructor
    static class FeedResponse {
        private Long id;
    }

    @GetMapping("/api/v2/feed/search")
    public List<FeedDto> searchFeedByKeyword(@RequestParam(value="q") String keyword) {
        List<Feed> feeds = feedService.searchFeedByKeyword(keyword);
        return feeds.stream()
                .map(o -> new FeedDto(o))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v2/feed/search/any")
    public List<FeedDto> searchFeedByAny(@RequestParam(value="q") String any) {
        List<Feed> feeds = feedService.searchFeedByAny(any);
        return feeds.stream()
                .map(o -> new FeedDto(o))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v2/feed")
    public List<FeedDto> feedAllV2() {
        List<Feed> feeds = feedService.findAll();
        return feeds.stream()
                .map(o -> new FeedDto(o))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v2/feed/{id}")
    public FeedDto fetchFeedById(@PathVariable(value = "id") Long feedId) {
        Feed feed = feedService.findFeedById(feedId);
        return new FeedDto(feed);
    }


    @Data
    @AllArgsConstructor
    static class UpdateFeedRequest {
        private Long feedId;
        private String feedText;
        private Tag feedTag;
    }

    @PostMapping("/api/v2/feed/update")
    public FeedDto updateFeed(@RequestBody @Validated UpdateFeedRequest request) {
        feedService.updateFeedText(request.feedId, request.feedText);
        feedService.updateFeedTag(request.feedId, request.feedTag);
        Feed feed = feedService.findFeedById(request.feedId);
        return new FeedDto(feed);
    }

    @Data
    @AllArgsConstructor
    static class FeedThreadResponse {
        private Long id;
    }

    @PostMapping("/api/v2/feed/thread/save")
    public FeedThreadResponse saveFeedThread(@RequestBody @Validated ThreadRequest request) {

        User findUser = userService.findOne(request.getUserId());
        Feed findFeed = feedService.findFeedById(request.getFeedId());

        FeedThread thread = new FeedThread();
        thread.setUser(findUser);
        thread.setFeed(findFeed);
        thread.setThreadText(request.getThreadText());
        thread.setDatetime();

        Long feedThreadId = feedService.saveFeedThread(thread);
        return new FeedThreadResponse(feedThreadId);
    }

    @Data
    @AllArgsConstructor
    static class UpdateFeedThreadRequest {
        private Long feedThreadId;
        private String threadText;
    }

    @PostMapping("/api/v2/feed/thread/update")
    public FeedThreadDto updateFeedThread(@RequestBody @Validated UpdateFeedThreadRequest request) {
        feedService.updateThreadText(request.feedThreadId, request.threadText);
        FeedThread feedThread = feedService.findThreadById(request.feedThreadId);
        return new FeedThreadDto(feedThread);
    }

    @Data
    @AllArgsConstructor
    static class FeedLikeResponse {
        private Long id;
    }

    @PostMapping("/api/v2/feed/like/act")
    public FeedLikeResponse actLike(@RequestBody @Validated LikeRequest request) {
        User findUser = userService.findOne(request.getUserId());
        Feed findFeed = feedService.findFeedById(request.getFeedId());

        Like like = new Like();
        like.setUser(findUser);
        like.setFeed(findFeed);
        like.setDatetime();

        Long likeId = feedService.saveFeedLike(like);
        return new FeedLikeResponse(likeId);
    }

    @PostMapping("/api/v2/feed/like/delete")
    public FeedLikeResponse deleteLike(@RequestBody @Validated LikeRequest request) {
        User findUser = userService.findOne(request.getUserId());
        Feed findFeed = feedService.findFeedById(request.getFeedId());

        Like like = new Like();
        like.setUser(findUser);
        like.setFeed(findFeed);
        like.setDatetime();

        Long likeId = feedService.deleteLike(like);
        return new FeedLikeResponse(likeId);
    }
}
