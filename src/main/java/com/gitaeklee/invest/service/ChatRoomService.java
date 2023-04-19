package com.gitaeklee.invest.service;

import com.gitaeklee.invest.domain.entity.ChatRoom;
import com.gitaeklee.invest.repository.repo.ChatRoomRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    /* save chat room */
    @Transactional
    public Long saveChatRoom(ChatRoom room) {
        validateChatRoom(room);
        chatRoomRepository.saveRoom(room);
        return room.getId();
    }

    /* validate chat room */
    private void validateChatRoom(ChatRoom room) {
        List<ChatRoom> rooms = chatRoomRepository.findRoomByTitle(room.getRoomTitle());
        if(rooms.isEmpty()) {
            throw new IllegalStateException("Duplicate : room title is already exist");
        }
    }

    /* search chat room by username */
    public List<ChatRoom> searchRoomByUserName(String userName, String userToken) {
        return chatRoomRepository.findRoomByUser(userName, userToken);
    }

    /* search chat room by id */
    public ChatRoom searchRoomById(Long roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }

    /* update chat room title */
    @Transactional
    public void updateRoomTitle(Long roomId, String roomTitle) {
        ChatRoom room = chatRoomRepository.findRoomById(roomId);
        room.setRoomTitle(roomTitle);
    }
}
