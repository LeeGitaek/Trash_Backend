package com.gitaeklee.invest.repository.repo;

import com.gitaeklee.invest.domain.entity.Feed;
import com.gitaeklee.invest.domain.entity.FeedThread;
import com.gitaeklee.invest.domain.entity.QFeedThread;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedThreadRepository {

    @PersistenceContext
    EntityManager em;
    private final JPAQueryFactory queryFactory;

    // add & update thread
    public void saveThread(FeedThread feedThread) {
        em.persist(feedThread);
    }

    public FeedThread findThreadOne(Long id) {
        QFeedThread thread = QFeedThread.feedThread;

        FeedThread findThread = queryFactory
                .selectFrom(thread)
                .where(thread.id.eq(id))
                .fetchOne();

        return findThread;
    }

    public List<FeedThread> findThreadByKeyword(String keyword) {
        if(keyword.length() == 0) return List.of();

        QFeedThread feedThread = QFeedThread.feedThread;

        List<FeedThread> result = queryFactory
                .selectFrom(feedThread)
                .where(feedThread.threadText.like(keyword))
                .fetch();

        return result;
    }

    // delete thread
    // edit thread
    // search thread
}
