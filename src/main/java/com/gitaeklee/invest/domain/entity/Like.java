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
@Table(name="sv_like")
public class Like {

    @Id @GeneratedValue
    private Long id;

    // like: user = n : 1
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    // like: feed = n: 1
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="feed_id")
    private Feed feed;

    @Column(name="action_date")
    private LocalDateTime datetime;

    /* related method */
    public void setUser(User user) {
        this.user = user;
        user.getLikes().add(this);
    }
    public void setFeed(Feed feed) {
        this.feed = feed;
        feed.getLikes().add(this);
    }

    /* entity setter & creator */
    public void setDatetime() {
        this.datetime = LocalDateTime.now();
    }
}
