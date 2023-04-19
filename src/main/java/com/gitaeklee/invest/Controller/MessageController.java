package com.gitaeklee.invest.Controller;

import com.gitaeklee.invest.domain.entity.ChatRoom;
import com.gitaeklee.invest.domain.entity.Message;
import com.gitaeklee.invest.domain.entity.User;
import com.gitaeklee.invest.repository.dto.MessageDto;
import com.gitaeklee.invest.repository.dto.request.MessageRequest;
import com.gitaeklee.invest.service.ChatRoomService;
import com.gitaeklee.invest.service.MessageService;
import com.gitaeklee.invest.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final UserService userService;
    private final ChatRoomService roomService;
    private final MessageService messageService;

    @PostMapping("/api/v2/message/send")
    public MessageResponse sendMessage(@RequestBody @Validated MessageRequest request) {
        User findUser = userService.findOne(request.getUserId());
        ChatRoom findRoom = roomService.searchRoomById(request.getRoomId());

        Message message = new Message();
        message.setMessageText(request.getMessage());
        message.setChatImage();
        message.setRoom(findRoom);
        message.setUser(findUser);
        message.setChatFile();
        message.setDatetime();
        message.setIsRead();

        messageService.sendMessage(message);
        findRoom.addMessage(message);
        return new MessageResponse(message.getId());
    }

    @Data
    @AllArgsConstructor
    static class MessageResponse {
        private Long messageId;
    }

    @GetMapping("/api/v2/message/in/{q}")
    public List<MessageDto> searchMessage(@PathVariable("q") String keyword) {
        List<Message> messages = messageService.findMessageByKeyword(keyword);
        List<MessageDto> result = messages.stream()
                .map(o -> new MessageDto(o))
                .collect(Collectors.toList());
        return result;
    }

    /* update message */
    @Data
    @AllArgsConstructor
    static class UpdateMessageRequest {
        private Long messageId;
        private String messageText;
    }

    @PostMapping("/api/v2/message/update")
    public MessageDto updateMessage(@RequestBody @Validated UpdateMessageRequest request) {
        messageService.updateMessage(request.messageId, request.messageText);
        Message message = messageService.findMessage(request.messageId);
        return new MessageDto(message);
    }

}
