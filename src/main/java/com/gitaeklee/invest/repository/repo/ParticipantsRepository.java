package com.gitaeklee.invest.repository.repo;

import com.gitaeklee.invest.domain.entity.Participants;
import com.gitaeklee.invest.domain.entity.QParticipants;
import com.gitaeklee.invest.domain.entity.QPaymentCard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class ParticipantsRepository {

    @PersistenceContext
    EntityManager em;
    private final JPAQueryFactory queryFactory;

    // add participant
    public void saveParticipant(Participants participant) {
        if(participant.getId() == null) {
            em.persist(participant);
        } else {
            em.merge(participant);
        }
    }

    // search participant one
    public Participants findParticipantOne(Long id) {
        QParticipants participant = QParticipants.participants;

        Participants findParticipant = queryFactory
                .selectFrom(participant)
                .where(participant.id.eq(id))
                .fetchOne();

        return findParticipant;
    }

}
