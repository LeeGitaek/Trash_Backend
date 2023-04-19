package com.gitaeklee.invest.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="sv_participants")
public class Participants {

    @Id @GeneratedValue
    @Column(name="participants_id")
    private Long id;

    // room
    // participants : room = n : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id")
    private ChatRoom room;

    //user
    //user : room = n : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    /* related method */
    public void setRoom(ChatRoom room) {
        // chat room setter
        this.room = room;
        room.getParticipants().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getParticipants().add(this);
    }
    /* entity setter & creator */
}
