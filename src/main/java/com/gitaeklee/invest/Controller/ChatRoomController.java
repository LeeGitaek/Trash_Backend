package com.gitaeklee.invest.Controller;

import com.gitaeklee.invest.domain.entity.ChatRoom;
import com.gitaeklee.invest.repository.dto.ChatRoomDto;
import com.gitaeklee.invest.service.ChatRoomService;
import com.querydsl.core.Tuple;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @Data
    @AllArgsConstructor
    static class ChatRoomResponse {
        private Long id;
    }

    @PostMapping("/api/v2/room/save")
    public ChatRoomResponse saveRoom(@RequestBody @Validated ChatRoom room) {
        Long roomId = chatRoomService.saveChatRoom(room);
        return new ChatRoomResponse(roomId);
    }

    @Data
    @AllArgsConstructor
    static class ChatRoomSearchRequest {
        private String userName;
        private String userToken;
    }

    @PostMapping("/api/v2/room/find")
    public List<ChatRoomDto> searchRoomByUserName(@RequestBody @Validated ChatRoomSearchRequest request) {
        List<ChatRoom> rooms = chatRoomService.searchRoomByUserName(request.userName, request.userToken);
        List<ChatRoomDto> result = rooms.stream()
                .map(o -> new ChatRoomDto(o))
                .collect(Collectors.toList());
        return result;
    }

    @GetMapping("/api/v2/room/{id}")
    public ChatRoomDto findRoomById(@PathVariable("id") Long roomId) {
        ChatRoom room = chatRoomService.searchRoomById(roomId);
        ChatRoomDto roomDto = new ChatRoomDto(room);
        return roomDto;
    }
}
