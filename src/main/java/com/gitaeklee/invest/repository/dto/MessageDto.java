package com.gitaeklee.invest.repository.dto;

import com.gitaeklee.invest.domain.entity.ChatRoom;
import com.gitaeklee.invest.domain.entity.Message;
import com.gitaeklee.invest.domain.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Getter
public class MessageDto {

    private Long messageId;
    private String chatText;
    private String chatImage;
    private String chatFile;
    private Long roomId;
    private Long userId;

    private String userName;
    private LocalDateTime datetime;

    private Boolean isRead;

    public MessageDto(Message message) {
        this.messageId = message.getId();
        this.chatText = message.getChatText();
        this.chatImage = message.getChatImage();
        this.chatFile = message.getChatFile();
        this.roomId = message.getRoom().getId();
        this.userId = message.getUser().getId();
        this.userName =message.getUser().getUserName();
        this.datetime = message.getDatetime();
        this.isRead = message.getIsRead();
    }

}
