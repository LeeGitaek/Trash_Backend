package com.gitaeklee.invest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


// q type 을 import static 으로 사용하면 깔끔하게 사용가능

@SpringBootTest
@Transactional
public class QuerydslBasicTest {
/*
    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;
    // 동시성 문제 없음.

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);

        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    public void startJPQL() {
        // member1
        Member findMember = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertEquals(findMember.getUsername(), "member1");
    }

    @Test
    public void startQuerydsl() {
        // QMember m = new QMember("m");
        QMember m = QMember.member;
        // new 키워드로 별칭을 직접 지정해서 생성해도 됨
        // 기본 인스턴스 생성자를 사용하는 방법 두가지 존재

        Member findMember = queryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("member1"))
                .fetchOne();

        assertEquals(findMember.getUsername(), "member1");
    }

    @Test
    public void search() {
        QMember m = QMember.member;
        Member findMember = queryFactory
                .selectFrom(m)
                .where(m.username.eq("member1")
                        .and(m.age.eq(10)))
                .fetchOne();

        assertEquals(findMember.getUsername(), "member1");
    }

    @Test
    public void searchAndParam() {
        QMember m = QMember.member;
        Member findMember = queryFactory
                .selectFrom(m)
                .where(
                        m.username.eq("member1"),
                        m.age.eq(10)
                )
                .fetchOne();

        assertEquals(findMember.getUsername(), "member1");
    }

    // 결과 조회
    // list => fetch
    // single => fetch one
    // fetch first => limit(1).fetchOne()
    // fetchResult() => 페이징 정보 포함, total query 추가 실행
    // fetchCount() => count 쿼리로 변경해서 count 수 조회

    /*
    @Test
    public void resultFetch() {

        QMember m = QMember.member;
        List<Member> fetch = queryFactory
                .selectFrom(m)
                .fetch();

        Member fetchOne = queryFactory
                .selectFrom(m)
                .fetchOne();

        Member fetchFirst = queryFactory
                .selectFrom(m)
                .fetchFirst();

        QueryResults<Member> results = queryFactory
                .selectFrom(m)
                .fetchResults();

        results.getTotal();
        List<Member> content = results.getResults();

        long total = queryFactory
                .selectFrom(m)
                .fetchCount();
    }
    */

    /*
    @Test
    public void sort() {

        QMember m = QMember.member;
        em.persist(new Member(null, 100));
        em.persist(new Member("member5", 100));
        em.persist(new Member("member6", 100));

        List<Member> result = queryFactory
                .selectFrom(m)
                .where(m.age.eq(100))
                .orderBy(m.age.desc(), m.username.asc().nullsLast())
                .fetch();

        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);

        assertEquals(member5.getUsername(), "member5");
        assertEquals(member6.getUsername(), "member6");
        assertEquals(memberNull.getUsername(), null);
    }
*/
    /*
    @Test
    public void paging1() {
        QMember m = QMember.member;
        List<Member> queryResult = queryFactory
                .selectFrom(m)
                .orderBy(m.username.desc())
                .offset(1)
                .limit(2)
                .fetch();

        assertEquals(queryResult.size(), 2);
    }

    @Test
    public void aggregation() {
        QMember m = QMember.member;

        List<Tuple> result = queryFactory
                .select(
                    m.count(),
                        m.age.sum(),
                        m.age.avg(),
                        m.age.max(),
                        m.age.min()
                )
                .from(m)
                .fetch();

        Tuple tuple = result.get(0);
        // 실무에서 tuple 많이 쓰지않고 dto 로 뽑는다.
        assertEquals(tuple.get(m.count()), 4);
        assertEquals(tuple.get(m.age.sum()), 100);
        assertEquals(tuple.get(m.age.avg()), 25);
        assertEquals(tuple.get(m.age.max()), 40);
        assertEquals(tuple.get(m.age.min()), 10);

    }

    @Test
    public void group() throws Exception {
        QTeam team = QTeam.team;
        QMember m = QMember.member;
        List<Tuple> result = queryFactory
                .select(team.name, m.age.avg())
                .from(m)
                .join(m.team, team)
                .groupBy(team.name)
                .fetch();

        Tuple teamA = result.get(0);
        Tuple teamB = result.get(1);

        assertEquals(teamA.get(team.name), "teamA");
        assertEquals(m.age.avg(), 15);

        assertEquals(teamB.get(team.name), "teamB");
        assertEquals(m.age.avg(), 35);
    }

    @Test
    public void join() {
        QTeam team = QTeam.team;
        QMember m = QMember.member;
        List<Member> result = queryFactory
                .selectFrom(m)
                .leftJoin(m.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("member1", "member2");

    }

    @Test
    public void theta_join() throws Exception {
        QTeam team = QTeam.team;
        QMember m = QMember.member;

        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));

        List<Member> result = queryFactory
                .select(m)
                .from(m, team)
                .where(m.username.eq(team.name))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("teamA", "teamB");

    }

    @Test
    public void join_on_filtering() {
        QTeam team = QTeam.team;
        QMember m = QMember.member;

        List<Tuple> result = queryFactory
                .select(m, team)
                .from(m)
                .leftJoin(m.team, team).on(team.name.eq("teamA"))
                .fetch();

        // on 절은 외부 조인할 때 사용하도록 해야함.

        for(Tuple tuple: result) {
            System.out.println("tuple :" + tuple);
        }
    }

    // 연관관계 없는 엔티티 외부조인
    @Test
    public void join_on_no_relation() throws Exception {
        QTeam team = QTeam.team;
        QMember m = QMember.member;

        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        em.persist(new Member("teamC"));

        List<Tuple> result = queryFactory
                .select(m, team)
                .from(m)
                .leftJoin(team).on(m.username.eq(team.name))
                .fetch();

        for(Tuple tuple: result) {
            System.out.println("tuple :" + tuple);
        }
    }

    @PersistenceUnit
    EntityManagerFactory emf;

    // fetch join -> 성능 최적화 사용방법
    @Test
    public void fetchJoinNo() {

        em.flush();
        em.clear();

        QMember m = QMember.member;

        Member findMember = queryFactory
                .selectFrom(m)
                .where(m.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).as("패치 조인 미적용").isFalse();

    }

    @Test
    public void fetchJoinUse() {
        em.flush();
        em.clear();

        QTeam team = QTeam.team;
        QMember m = QMember.member;

        Member findMember = queryFactory
                .selectFrom(m)
                .join(m.team, team).fetchJoin()
                .where(m.username.eq("member1"))
                .fetchOne();

        boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertThat(loaded).as("패치 조인 적용").isTrue();

    }

    @Test
    public void subQuery() {
        QTeam team = QTeam.team;
        QMember m = QMember.member;
        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(m)
                .where(m.age.eq(
                        JPAExpressions
                                .select(memberSub.age.max())
                                .from(memberSub)
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(40);

    }

    @Test
    public void subQueryGoe() {

        QMember m = QMember.member;
        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(m)
                .where(m.age.goe(
                        JPAExpressions
                                .select(memberSub.age.avg())
                                .from(memberSub)
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(30,40);

    }

    @Test
    public void subQueryIn() {

        QMember m = QMember.member;
        QMember memberSub = new QMember("memberSub");

        List<Member> result = queryFactory
                .selectFrom(m)
                .where(m.age.in(
                        JPAExpressions
                                .select(memberSub.age)
                                .from(memberSub)
                                .where(memberSub.age.gt(10))
                ))
                .fetch();

        assertThat(result).extracting("age")
                .containsExactly(20, 30, 40);

    }

    @Test
    public void selectSubQuery() {
        QMember m = QMember.member;
        QMember memberSub = new QMember("memberSub");


        List<Tuple> result = queryFactory
                .select(m.username,
                        JPAExpressions
                                .select(memberSub.age.avg())
                                .from(memberSub)
                )
                .from(m)
                .fetch();

        for(Tuple tuple: result) {
            System.out.println("tuple = "  + tuple);
        }
    }

    @Test
    public void basicCase() {
        QMember m = QMember.member;

        List<String> result = queryFactory
                .select(m.age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .otherwise("기타"))
                .from(m)
                .fetch();

        for(String s : result) {
            System.out.println("s =" + s);
        }
    }

    @Test
    public void complexCase() {
        QMember m = QMember.member;

        List<String> result = queryFactory
                .select(new CaseBuilder()
                        .when(m.age.between(0, 20)).then("0~20")
                        .when(m.age.between(21,30)).then("21~30")
                        .otherwise("기타"))
                .from(m)
                .fetch();

        for(String s : result) {
            System.out.println("s = "+ s);
        }
    }

    @Test
    public void constant() {
        QMember m = QMember.member;

        List<Tuple> result = queryFactory
                .select(m.username, Expressions.constant("A"))
                .from(m)
                .fetch();

        for(Tuple tuple: result) {
            System.out.println("Tuple: "+tuple);
        }
    }

    @Test
    public void concat() {
        QMember m = QMember.member;

        List<String> result = queryFactory
                .select(m.username.concat("_").concat(m.age.stringValue()))
                .from(m)
                .where(m.username.eq("member1"))
                .fetch();

        for(String s : result) {
            System.out.println("s = "+ s);
        }
    }

    @Test
    public void simpleProjection() {
        QMember m = QMember.member;

        List<String> result = queryFactory
                .select(m.username)
                .from(m)
                .fetch();

        for(String s: result) {
            System.out.println("s = "+ s);
        }
    }

    @Test
    public void tupleProjection() {
        QMember m = QMember.member;

        List<Tuple> result = queryFactory
                .select(m.username, m.age)
                .from(m)
                .fetch();

        for(Tuple tuple: result) {
            String username = tuple.get(m.username);
            Integer age = tuple.get(m.age);

            System.out.println("username:"+username);
            System.out.println("age = " + age);
        }
    }

    @Test
    public void findDtoByJPQL() {
        QMember m = QMember.member;

        List<MemberDto> result = em.createQuery("select new com.gitaeklee.invest.dto.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                .getResultList();

        for(MemberDto memberDto: result) {
            System.out.println("memberDto =" +memberDto);
        }
    }

    @Test
    public void findDtoBySetter() {
        QMember m = QMember.member;

        List<MemberDto> result = queryFactory
                .select(Projections.bean(MemberDto.class,
                        m.username,
                        m.age))
                .from(m)
                .fetch();
        for(MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoByField() {
        QMember m = QMember.member;

        List<MemberDto> result = queryFactory
                .select(Projections.fields(MemberDto.class,
                        m.username,
                        m.age))
                .from(m)
                .fetch();
        for(MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findDtoByConstructor() {
        QMember m = QMember.member;

        List<MemberDto> result = queryFactory
                .select(Projections.constructor(MemberDto.class,
                        m.username,
                        m.age))
                .from(m)
                .fetch();
        for(MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    public void findUserDto() {
        QMember m = QMember.member;

        List<UserDto> result = queryFactory
                .select(Projections.fields(UserDto.class,
                        m.username.as("name"),
                        m.age))
                .from(m)
                .fetch();

        for(UserDto userDto : result) {
            System.out.println("memberDto = " + userDto);
        }
    }
    */
}
