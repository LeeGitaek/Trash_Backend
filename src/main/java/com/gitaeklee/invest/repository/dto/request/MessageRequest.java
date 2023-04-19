package com.gitaeklee.invest.repository.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageRequest {

    private Long roomId;
    private Long userId;
    private String message;
}
