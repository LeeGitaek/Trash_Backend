package com.gitaeklee.invest.repository.repo;

import com.gitaeklee.invest.domain.entity.Message;
import com.gitaeklee.invest.domain.entity.QMessage;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessageRepository {

    @PersistenceContext
    EntityManager em;
    private final JPAQueryFactory queryFactory;

    // save & update message
    public void sendMessage(Message message) {
        if(message.getId() == null) {
            em.persist(message);
        } else {
            em.merge(message);
        }
    }

    public Message findMessage(Long id) {
        QMessage message = QMessage.message;

        Message findMessage = queryFactory
                .selectFrom(message)
                .where(message.id.eq(id))
                .fetchOne();

        return findMessage;
    }

    public List<Message> findMessageByKeyword(String keyword) {
        if(keyword.length() == 0) return List.of();

        QMessage message = QMessage.message;

        List<Message> result = queryFactory
                .selectFrom(message)
                .where(message.chatText.like(keyword))
                .fetch();

        return result;
    }
}
