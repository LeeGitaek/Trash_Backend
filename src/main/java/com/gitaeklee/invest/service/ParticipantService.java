package com.gitaeklee.invest.service;

import com.gitaeklee.invest.domain.entity.ChatRoom;
import com.gitaeklee.invest.domain.entity.Participants;
import com.gitaeklee.invest.domain.entity.User;
import com.gitaeklee.invest.repository.repo.ParticipantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParticipantService {

    private final ParticipantsRepository participantsRepository;

    /* find one participant */
    public Participants findParticipant(Long participantId) {
        return participantsRepository.findParticipantOne(participantId);
    }

    /* add participant */
    public Long addParticipant(Participants participant) {
        validateParticipant(participant);
        participantsRepository.saveParticipant(participant);
        return participant.getId();
    }

    private void validateParticipant(Participants participant) {
        Participants findParticipant = findParticipant(participant.getId());
        if(findParticipant.getRoom() == null) {
            throw new IllegalStateException("Error: participant room is not set");
        }

        if(findParticipant.getUser() == null) {
            throw new IllegalStateException("Error: participant user is not set");
        }
    }

    /* update participant room */
    @Transactional
    public void updateParticipantRoom(Long participantId, ChatRoom room) {
        Participants participant = findParticipant(participantId);
        participant.setRoom(room);
    }

    /* update participant user */
    @Transactional
    public void updateParticipantUser(Long participantId, User user) {
        Participants participant = findParticipant(participantId);
        participant.setUser(user);
    }
}
