package com.gitaeklee.invest.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="sv_thread")
public class FeedThread {

    @Id @GeneratedValue
    private Long id;

    // thread: user = n : 1
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    // thread: feed = n: 1
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="feed_id")
    private Feed feed;

    @Column(name="thread_text")
    private String threadText;

    @Column(name="action_date")
    private LocalDateTime datetime;

    /* related method */
    public void setUser(User user) {
        this.user = user;
        user.getThreads().add(this);
    }
    public void setFeed(Feed feed) {
        this.feed = feed;
        feed.getThreads().add(this);
    }

    /* entity setter & creator */
    public void setThreadText(String threadText) {
        this.threadText = threadText;
    }

    public void setDatetime() {
        this.datetime = LocalDateTime.now();
    }
}
