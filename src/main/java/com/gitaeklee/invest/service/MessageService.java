package com.gitaeklee.invest.service;

import com.gitaeklee.invest.domain.entity.Message;
import com.gitaeklee.invest.repository.repo.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    /* find a message */
    public Message findMessage(Long id) {
        return messageRepository.findMessage(id);
    }

    /* send a message */
    @Transactional
    public Long sendMessage(Message message) {
        validateEmptyMessage(message);
        messageRepository.sendMessage(message);
        return message.getId();
    }

    /* validate empty message */
    private void validateEmptyMessage(Message message) {
        if(message.getChatText().length() == 0) {
            throw new IllegalStateException("Error : empty message can't send.");
        }
    }

    /* search messages by keyword */
    public List<Message> findMessageByKeyword(String keyword) {
        return messageRepository.findMessageByKeyword(keyword);
    }

    /* update chat text */
    @Transactional
    public void updateMessage(Long messageId, String chatText) {
        Message msg = findMessage(messageId);
        msg.setMessageText(chatText);
    }

}
