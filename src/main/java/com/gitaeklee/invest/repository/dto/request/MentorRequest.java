package com.gitaeklee.invest.repository.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MentorRequest {

    private Long userId;
    private int price;

}
