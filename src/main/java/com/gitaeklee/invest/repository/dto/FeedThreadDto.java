package com.gitaeklee.invest.repository.dto;

import com.gitaeklee.invest.domain.entity.Feed;
import com.gitaeklee.invest.domain.entity.FeedThread;
import com.gitaeklee.invest.domain.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Getter
public class FeedThreadDto {
    private Long threadId;
    private Long userId;
    private String userName;
    private String feedToken;
    private String threadText;
    private LocalDateTime datetime;
    public FeedThreadDto(FeedThread feedThread) {
        this.threadId = feedThread.getId();
        this.userId = feedThread.getUser().getId();
        this.userName = feedThread.getUser().getUserName();
        this.feedToken = feedThread.getFeed().getFeedToken();
        this.threadText = feedThread.getThreadText();
        this.datetime = feedThread.getDatetime();
    }

}
