package com.gitaeklee.invest.repository.dto;

import com.gitaeklee.invest.domain.entity.ChatRoom;
import com.gitaeklee.invest.domain.entity.Message;
import com.gitaeklee.invest.domain.entity.MessageType;
import com.gitaeklee.invest.domain.entity.Participants;
import com.querydsl.core.Tuple;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Getter
public class ChatRoomDto {
    private Long roomId;
    private String roomTitle;

    private List<ParticipantsDto> participants = new ArrayList<>();

    private List<MessageDto> messages = new ArrayList<>();
    private MessageType type;

    public ChatRoomDto(ChatRoom room) {
        this.roomId = room.getId();
        this.roomTitle = room.getRoomTitle();
        this.participants = room.getParticipants()
                .stream().map(o -> new ParticipantsDto(o))
                .collect(Collectors.toList());
        this.messages = room.getMessages()
                .stream().map(o -> new MessageDto(o))
                .collect(Collectors.toList());
        this.type = room.getType();
    }
}
