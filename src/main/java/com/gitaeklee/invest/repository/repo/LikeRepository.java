package com.gitaeklee.invest.repository.repo;

import com.gitaeklee.invest.domain.entity.Like;
import com.gitaeklee.invest.domain.entity.QLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;
    // add like
    public void saveLike(Like like) { em.persist(like); }
    // delete like
    public Like findLike(Long userId, Long postId) {
        QLike findLike = QLike.like;

        Like like = queryFactory
                .selectFrom(findLike)
                .where(
                        findLike.user.id.eq(userId)
                                .and(findLike.feed.id.eq(postId))
                )
                .fetchOne();

        return like;
    }

    public Long deleteLike(Long userId, Long postId) {
        QLike like = QLike.like;

        Long deletelike = queryFactory
                .delete(like)
                .where(
                        like.user.id.eq(userId)
                                .and(like.feed.id.eq(postId))
                )
                .execute();

        return deletelike;
    }

}
