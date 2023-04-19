package com.gitaeklee.invest.repository.repo;

import com.gitaeklee.invest.domain.entity.Feed;
import com.gitaeklee.invest.domain.entity.QFeed;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.function.Predicate;

@Repository
@RequiredArgsConstructor
public class FeedRepository {

    @PersistenceContext
    EntityManager em;
    private final JPAQueryFactory queryFactory;

    // register & update feed
    public void saveFeed(Feed feed) {
        em.persist(feed);
    }

    public List<Feed> findAll() {
        QFeed feed = QFeed.feed;

        List<Feed> feeds = queryFactory
                .selectFrom(feed)
                .orderBy(feed.datetime.desc())
                .fetch();

        return feeds;
    }

    /* search feed */
    public Feed findFeedById(Long id) {
        QFeed feed = QFeed.feed;

        Feed findFeed = queryFactory
                .selectFrom(feed)
                .where(feed.id.eq(id))
                .fetchOne();

        return findFeed;
    }

    public List<Feed> findFeedByKeyword(String keyword) {
        if(keyword.length() == 0) return List.of();

        QFeed feed = QFeed.feed;

        List<Feed> result = queryFactory
                .selectFrom(feed)
                .where(feed.feedText.like(keyword))
                .fetch();

        return result;
    }

    public List<Feed> findFeedByAny(String q) {
        if(q.length() == 0) return List.of();

        QFeed feed = QFeed.feed;

        List<Feed> result = queryFactory
                .selectFrom(feed)
                .where(isFindByAny(q))
                .fetch();

        return result;
    }

    public BooleanExpression isFindByAny(String q) {
        return isSearchableInFeedText(q)
                .or(isSearchableInUser(q))
                .or(isSearchableInTag(q));
    }
    public BooleanExpression isSearchableInFeedText(String keyword) {
        QFeed feed = QFeed.feed;
        return feed.feedText.like(keyword);
    }

    public BooleanExpression isSearchableInUser(String name) {
        QFeed feed = QFeed.feed;
        return feed.user.userName.like(name);
    }

    public BooleanExpression isSearchableInTag(String tag) {
        QFeed feed = QFeed.feed;
        return feed.tag.tagName.like(tag);
    }
    /* search feed end */


    // recommend feed, ...

}
