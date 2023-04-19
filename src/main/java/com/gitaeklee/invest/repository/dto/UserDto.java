package com.gitaeklee.invest.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitaeklee.invest.domain.entity.*;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
public class UserDto {

    private Long userid;
    private String userName;
    private String email;
    private String password;
    private String phone;

    private Address address;
    private String profileImage;
    private String profileIntro;
    private LocalDateTime datetime;
    private float trustPercent;
    private boolean isCrewMember;

    private String userToken;

    private Long mentorId;

    public UserDto(User user) {
        this.userid = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.profileImage = user.getProfileImage();
        this.profileIntro = user.getProfileIntro();
        this.datetime = user.getDatetime();
        this.trustPercent = user.getTrustPercent();
        this.isCrewMember = user.isCrewMember();
        this.userToken = user.getUserToken();
        if (user.getMentor() == null) {
            this.mentorId = 0L;
        } else {
            this.mentorId = user.getMentor().getId();
        }
    }
}
