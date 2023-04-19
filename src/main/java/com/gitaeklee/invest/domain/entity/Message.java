package com.gitaeklee.invest.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name="sv_message")
public class Message {

    @Id @GeneratedValue
    @Column(name="message_id")
    private Long id;

    @Column(name="chat_text")
    private String chatText;

    @Column(name="chat_image")
    private String chatImage;

    @Column(name="chat_file")
    private String chatFile;

    // message : room = n : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="room_id")
    private ChatRoom room;

    // message : user = n : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="send_date")
    private LocalDateTime datetime;

    @Column(name="is_read")
    private Boolean isRead;

    /* related method */
    public void setRoom(ChatRoom room) {
        // message room setter
        this.room = room;
        room.getMessages().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getMessages().add(this);
    }

    /* entity setter & creator */
    public void setMessageText(String chatText) {
        this.chatText = chatText;
    }

    // 채팅 읽음 데이터
    public void setIsRead() {
        this.isRead = false;
    }

    public void setDatetime() {
        this.datetime = LocalDateTime.now();
    }

    public void setChatImage() {
        this.chatImage = "";
    }

    public void setChatFile() {
        this.chatFile = "";
    }
}
