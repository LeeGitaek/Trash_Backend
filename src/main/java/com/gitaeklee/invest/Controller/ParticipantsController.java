package com.gitaeklee.invest.Controller;

import com.gitaeklee.invest.domain.entity.ChatRoom;
import com.gitaeklee.invest.domain.entity.Participants;
import com.gitaeklee.invest.domain.entity.User;
import com.gitaeklee.invest.repository.dto.ParticipantsDto;
import com.gitaeklee.invest.service.ChatRoomService;
import com.gitaeklee.invest.service.ParticipantService;
import com.gitaeklee.invest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParticipantsController {

    private final ParticipantService participantService;
    private final UserService userService;
    private final ChatRoomService roomService;

    @PostMapping("/api/v2/participant/add")
    public ParticipantResponse addParticipant(@RequestBody @Validated Participants participant) {
        participantService.addParticipant(participant);
        return new ParticipantResponse(participant.getId());
    }

    @Data
    @AllArgsConstructor
    static class ParticipantResponse {
        private Long id;
    }

    /* update participant */

    @Data
    @AllArgsConstructor
    static class UpdateParticipantRequest {
        private Long participantId;
        private Long userId;
        private Long roomId;
    }

    @PostMapping("/api/v2/participant/update")
    public ParticipantsDto updateParticipant(@RequestBody @Validated UpdateParticipantRequest request) {
        User user = userService.findOne(request.userId);
        ChatRoom room = roomService.searchRoomById(request.roomId);
        participantService.updateParticipantUser(request.participantId, user);
        participantService.updateParticipantRoom(request.participantId, room);
        Participants participant = participantService.findParticipant(request.participantId);
        return new ParticipantsDto(participant);
    }
}
