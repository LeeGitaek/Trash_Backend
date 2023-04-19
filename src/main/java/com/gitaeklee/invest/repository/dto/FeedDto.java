package com.gitaeklee.invest.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitaeklee.invest.domain.entity.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Getter
public class FeedDto {
    private Long feedId;
    private Long userId;
    private String userName;

    private String userCity;
    private String userState;

    private Long tagId;
    private String tagName;

    private List<LikeDto> likes = new ArrayList<>();
    private List<FeedThreadDto> threads = new ArrayList<>();

    private String feedText;
    private LocalDateTime postdate;
    private Long viewCount;
    private int threadCount;
    private int likeCount;
    private String feedToken;

    private Boolean isLiked;

    public FeedDto(Feed feed) {
        this.feedId = feed.getId();
        this.userId = feed.getUser().getId();
        this.userName = feed.getUser().getUserName();
        this.userCity = feed.getUser().getAddress().getCity();
        this.userState = feed.getUser().getAddress().getState();
        this.tagId = feed.getTag().getId();
        this.tagName = feed.getTag().getTagName();
        this.feedText = feed.getFeedText();
        this.postdate = feed.getDatetime();
        this.viewCount = feed.getViewCount();
        this.threadCount = feed.getThreads().stream()
                .map(o -> new FeedThreadDto(o))
                .collect(Collectors.toList()).size();
        this.likeCount = feed.getLikes().stream()
                .map(o -> new LikeDto(o))
                .collect(Collectors.toList()).size();
        this.feedToken = feed.getFeedToken();
        this.likes = feed.getLikes().stream()
                .map(o -> new LikeDto(o))
                .collect(Collectors.toList());
        this.threads = feed.getThreads().stream()
                .map(o -> new FeedThreadDto(o))
                .collect(Collectors.toList());
        this.isLiked = feed.getLikes().stream()
                .map(o -> new LikeDto(o))
                .collect(Collectors.toList())
                .stream().anyMatch(o -> o.getUserId().equals(this.userId));
    }
}
