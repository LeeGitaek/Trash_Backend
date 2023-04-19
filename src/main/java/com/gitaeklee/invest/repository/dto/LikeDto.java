package com.gitaeklee.invest.repository.dto;

import com.gitaeklee.invest.domain.entity.Feed;
import com.gitaeklee.invest.domain.entity.Like;
import com.gitaeklee.invest.domain.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Getter
public class LikeDto {
    private Long likeId;
    private Long userId;
    private String feedToken;
    private LocalDateTime actionDate;

    public LikeDto(Like like) {
        this.likeId = like.getId();
        this.userId = like.getUser().getId();
        this.feedToken = like.getFeed().getFeedToken();
        this.actionDate = like.getDatetime();
    }
}
