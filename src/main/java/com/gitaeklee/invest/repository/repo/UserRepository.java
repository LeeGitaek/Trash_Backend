package com.gitaeklee.invest.repository.repo;

import com.gitaeklee.invest.domain.entity.*;
import com.gitaeklee.invest.repository.dto.UserDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    private final JPAQueryFactory queryFactory;

    // 동시성 문제 없음.

    // save & edit
    public void saveUser(User user) {
        em.persist(user);
    }

    // search user such as like a name
    public List<Tuple> searchUserByName(String userName) {
        QUser user = QUser.user;

        List<Tuple> result = queryFactory
                .select()
                .from(user)
                .where(user.userName.like(userName))
                .fetch();

        return result;
    }

    public List<User> findByName(String name) {
        return em.createQuery("select m from User m where m.userName = :userName", User.class)
                .setParameter("userName", name)
                .getResultList();
    }

    // find user by name
    public User findUserByName(String userName) {
        QUser user = QUser.user;

        User result = queryFactory
                .selectFrom(user)
                .where(user.userName.eq(userName))
                .fetchOne();

        return result;
    }

    // search find one user
    public User findUser(Long id) {
        QUser user = QUser.user;

        User findUser = queryFactory
                .selectFrom(user)
                .where(user.id.eq(id))
                .fetchOne();

        return findUser;
    }

    public User findUserByToken(Long id, String userToken) {
        QUser user = QUser.user;

        User findUser = queryFactory
                .selectFrom(user)
                .where(
                        user.id.eq(id),
                        user.userToken.eq(userToken)
                )
                .fetchOne();

        return findUser;

    }

    public User findUserByUserEmail(String userEmail) {
        QUser user = QUser.user;

        User findUser = queryFactory
                .selectFrom(user)
                .where(
                        user.email.eq(userEmail)
                )
                .fetchOne();

        return findUser;
    }

    // get all user
    public List<User> findAll() {
        QUser user = QUser.user;

        List<User> result = queryFactory
                .selectFrom(user)
                .fetch();

        return result;
    }

    // paid config
    public List<Tuple> payConfig(Long id) {
        QPayment payment = QPayment.payment;
        QPaymentCard card = QPaymentCard.paymentCard;

        List<Tuple> result = queryFactory
                .select(payment, card)
                .from(payment)
                .leftJoin(payment.card, card)
                    .on(card.user.id.eq(payment.card.user.id))
                .fetch();

        return result;
    }
}
