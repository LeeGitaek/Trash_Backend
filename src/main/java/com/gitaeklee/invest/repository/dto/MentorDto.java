package com.gitaeklee.invest.repository.dto;

import com.gitaeklee.invest.domain.entity.Mentor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Getter
public class MentorDto {

    private Long mentorId;
    private Long userId;

    private String userCity;

    private String userState;
    private String userName;
    private String userIntro;
    private int price;
    private LocalDateTime datetime;

    public MentorDto(Mentor mentor) {
        this.mentorId = mentor.getId();
        this.userId = mentor.getUser().getId();
        this.userCity = mentor.getUser().getAddress().getCity();
        this.userState = mentor.getUser().getAddress().getState();
        this.userName = mentor.getUser().getUserName();
        this.userIntro = mentor.getUser().getProfileIntro();
        this.price = mentor.getPrice();
        this.datetime = mentor.getDatetime();
    }
}
