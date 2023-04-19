package com.gitaeklee.invest.repository.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ThreadRequest {
    private Long userId;
    private Long feedId;
    private String threadText;
}
