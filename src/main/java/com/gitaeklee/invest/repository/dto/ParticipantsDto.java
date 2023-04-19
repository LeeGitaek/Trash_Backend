package com.gitaeklee.invest.repository.dto;

import com.gitaeklee.invest.domain.entity.ChatRoom;
import com.gitaeklee.invest.domain.entity.Participants;
import com.gitaeklee.invest.domain.entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Getter
public class ParticipantsDto {
    private Long participantId;
    private Long roomId;
    private Long userId;
    private String userName;

    public ParticipantsDto(Participants participants) {
        this.participantId = participants.getId();
        this.roomId = participants.getRoom().getId();
        this.userId = participants.getUser().getId();
        this.userName = participants.getUser().getUserName();
    }
}
