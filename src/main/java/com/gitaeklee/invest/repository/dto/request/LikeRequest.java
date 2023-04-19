package com.gitaeklee.invest.repository.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeRequest {
    private Long userId;
    private Long feedId;
}
