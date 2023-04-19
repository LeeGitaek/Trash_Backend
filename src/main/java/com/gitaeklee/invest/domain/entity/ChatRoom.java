package com.gitaeklee.invest.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="sv_chatroom")
public class ChatRoom {

    @Id @GeneratedValue
    @Column(name="room_id")
    private Long id;

    @Column(name="room_title")
    private String roomTitle;

    // room : participants = 1: n
    @OneToMany(mappedBy = "room")
    private List<Participants> participants = new ArrayList<>();

    // room : message = 1 : n
    @OneToMany(mappedBy = "room")
    private List<Message> messages = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MessageType type; // message type
    // type = group or private => enum type

    /* related method */
    public void addParticipants(Participants participant) {
        // add participants to list
        participants.add(participant);
        participant.setRoom(this);
    }

    public void addMessage(Message message) {
        // add message to list
        messages.add(message);
        message.setRoom(this);
    }

    /* entity setter & creator */
    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }
    public void setType(MessageType type) { this.type = type; }
}
