package com.gitaeklee.invest.repository.repo;

import com.gitaeklee.invest.domain.entity.QSearch;
import com.gitaeklee.invest.domain.entity.QUser;
import com.gitaeklee.invest.domain.entity.Search;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchRepository {

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    // save search
    public void saveSearch(Search search) {
        if(search.getKeyword() == null) {
            em.persist(search);
        } else {
            return;
        }
    }

    // admin - all search keyword
    public List<Search> findSearchAll() {
        QSearch search = QSearch.search;

        List<Search> result = queryFactory
                .selectFrom(search)
                .fetch();

        return result;
    }

    public void deleteSearch(Search search) {
        // delete search
    }

    public List<Search> findSearchByUserId(Long userId) {
        QSearch search = QSearch.search;

        List<Search> result = queryFactory
                .selectFrom(search)
                .where(search.user.id.eq(userId))
                .fetch();

        return result;
    }

    // recommend?
}
