package com.gitaeklee.invest.repository.repo;

import com.gitaeklee.invest.domain.entity.QTag;
import com.gitaeklee.invest.domain.entity.Tag;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagRepository {

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;
    // save & update
    public void saveTag(Tag tag) {
        em.persist(tag);
    }

    // find one
    public Tag findOne(Long id) {
        QTag tag = QTag.tag;

        Tag findTag = queryFactory
                .selectFrom(tag)
                .where(tag.id.eq(id))
                .fetchOne();

        return findTag;
    }

    // find a tag by name
    public Tag findTagByName(String tagName) {
        QTag tag = QTag.tag;

        Tag findTags = queryFactory
                .selectFrom(tag)
                .where(tag.tagName.eq(tagName))
                .fetchOne();

        return findTags;
    }

    public List<Tag> findAll() {
        QTag tag = QTag.tag;

        List<Tag> result = queryFactory
                .selectFrom(tag)
                .fetch();

        return result;
    }
}
