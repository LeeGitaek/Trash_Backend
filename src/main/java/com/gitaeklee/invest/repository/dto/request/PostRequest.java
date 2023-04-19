package com.gitaeklee.invest.repository.dto.request;

import com.gitaeklee.invest.domain.entity.Tag;
import com.gitaeklee.invest.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRequest {
    private Long userId;
    private String tagName;
    private String feedText;
}
