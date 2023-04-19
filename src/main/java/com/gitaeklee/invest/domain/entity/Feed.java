package com.gitaeklee.invest.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="sv_feed")
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="feed_id", unique = true)
    private Long id;

    // email
    // feed : user = n : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = false)
    private User user;

    // related tag feed n : 1 tag
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", unique = false)
    private Tag tag;

    // feed : like = 1: n
    @JsonIgnore
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL) // 매핑된 것.
    private List<Like> likes = new ArrayList<>();

    // feed : thread = 1: n
    @JsonIgnore
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL) // 매핑된 것.
    private List<FeedThread> threads = new ArrayList<>();

    @Column(name="feed_text", unique = false)
    private String feedText;

    @Column(name="post_date")
    private LocalDateTime datetime;

    @Column(name="view_count")
    private Long viewCount;

    @Column(name="thread_count")
    private int threadCount;

    @Column(name="like_count")
    private int likeCount;

    @Column(name="feed_token")
    private String feedToken;

    /* related method */
    public void setUser(User user) {
        // user setter in feed
        this.user = user;
        user.getFeeds().add(this);
    }

    public void setTag(Tag tag) {
        // tag setter in feed
        this.tag = tag;
    }

    public void addLike(Like like) {
        // like setter in feed
        likes.add(like);
        like.setFeed(this);
    }

    public void addThread(FeedThread feedThread) {
        threads.add(feedThread);
        feedThread.setFeed(this);
    }

    /* entity setter & creator */
    public void setFeedText(String feedText) {
        this.feedText = feedText;
    }

    public void setFeedTag(Tag tag) {
        this.tag = tag;
    }

    public void setViewCount() {
        this.viewCount = 0L;
    }

    public void setThreadCount() {
        this.threadCount = 0;
    }

    public void setLikeCount() {
        this.likeCount = 0;
    }

    public void setFeedToken(String feedToken) {
        this.feedToken = feedToken;
    }

    public void setDatetime() {
        this.datetime = LocalDateTime.now();
    }
}
