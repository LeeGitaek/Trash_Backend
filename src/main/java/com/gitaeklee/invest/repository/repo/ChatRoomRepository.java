package com.gitaeklee.invest.repository.repo;

import com.gitaeklee.invest.domain.entity.*;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository {

    @PersistenceContext
    EntityManager em;
    private final JPAQueryFactory queryFactory;

    // add chat room
    public void saveRoom(ChatRoom room) {
        if(room.getId() == null) {
            em.persist(room);
        } else {
            em.merge(room);
        }
    }

    public ChatRoom findRoomById(Long id) {
        QChatRoom room = QChatRoom.chatRoom;

        ChatRoom findRoom = queryFactory
                .selectFrom(room)
                .where(room.id.eq(id))
                .fetchOne();

        return findRoom;
    }

    public List<ChatRoom> findRoomByUser(String userName, String userToken) {
        QChatRoom chatRoom = QChatRoom.chatRoom;
        QParticipants participants = QParticipants.participants;

        List<ChatRoom> result = queryFactory
                .selectFrom(chatRoom)
                .leftJoin(chatRoom.participants, participants)
                .on(participants.user.userName.eq(userName)
                        .and(participants.user.userToken.eq(userToken)))
                .fetch();

        return result;
    }

    public List<ChatRoom> findRoomByTitle(String title) {
        if(title.length() == 0) return List.of();

        QChatRoom room = QChatRoom.chatRoom;

        List<ChatRoom> result = queryFactory
                .selectFrom(room)
                .where(room.roomTitle.eq(title))
                .fetch();

        return result;
    }
}
