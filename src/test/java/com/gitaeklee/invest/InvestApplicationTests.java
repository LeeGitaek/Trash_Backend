package com.gitaeklee.invest;

import com.gitaeklee.invest.domain.entity.QUser;
import com.gitaeklee.invest.domain.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class InvestApplicationTests {
	@PersistenceContext
	EntityManager em;

	JPAQueryFactory queryFactory;
	// 동시성 문제 없음.

	@BeforeEach
	public void before() {
		queryFactory = new JPAQueryFactory(em);
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void userByNameTest() {
		QUser m = QUser.user;
		User findMember = queryFactory
				.selectFrom(m)
				.where(m.userName.eq("gitaeklee"))
				.fetchOne();

		assertEquals(findMember.getUserName(), "gitaeklee");
	}

}
