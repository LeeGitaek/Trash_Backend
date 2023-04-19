package com.gitaeklee.invest.domain.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="sv_mentor")
public class Mentor {

    @Id @GeneratedValue
    private Long id;

    // user : mentor = 1 : 1
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", unique = true)
    private User user;

    // for an hour
    private int price;

    private LocalDateTime datetime;

    // related setter
    public void setUser(User user) {
        this.user = user;
        user.setMentor(this);
    }

    // setter
    public void setDatetime() {
        this.datetime = LocalDateTime.now();
    }

    public void setPrice(int hour) {
        this.price = hour;
    }
}
