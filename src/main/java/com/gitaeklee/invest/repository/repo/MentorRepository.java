package com.gitaeklee.invest.repository.repo;

import com.gitaeklee.invest.domain.entity.Mentor;
import com.gitaeklee.invest.domain.entity.QMentor;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MentorRepository {

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    public List<Mentor> findAll() {
        QMentor mentor = QMentor.mentor;

        List<Mentor> mentors = queryFactory
                .selectFrom(mentor)
                .orderBy(mentor.datetime.desc())
                .fetch();

        return mentors;
    }

    public void saveMentor(Mentor mentor) {
        em.persist(mentor);
    }

    public Mentor findById(Long id) {
        QMentor mentor = QMentor.mentor;

        Mentor findMentor = queryFactory
                .selectFrom(mentor)
                .where(mentor.user.id.eq(id))
                .fetchOne();

        return findMentor;
    }

    public Mentor findByName(String userName) {
        QMentor mentor = QMentor.mentor;

        Mentor findMentor = queryFactory
                .selectFrom(mentor)
                .where(mentor.user.userName.eq(userName))
                .fetchOne();

        return findMentor;
    }

}
