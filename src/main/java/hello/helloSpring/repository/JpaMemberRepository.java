package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // JPA는 EntityManager로 동작을 진행.
    // Spring boot가 자동으로 EntityManager를 동작해줌.
    // JPA를 쓰려면 EntityManager라는 거를 주입 받아야한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // persist 영속하다, 영구저장하다.
        em.persist(member);
        // JPA가 알아서 다 해줌.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // 저장하고 조회하고 업데이트하고 딜리트하는건 SQL을 짤필요가 없음
    // PK기반이 아닌 나머지들은 JPQL이라는걸 작성해야됨.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name" , Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
//        List<Member> result = em.createQuery("select m from Member m", Member.class)
//                .getResultList();
//
//        return result;
        // CTRL + ALT + N
        // 기존 SQL 같은경우 select *, m.id, m.name으로 조회를 했는데 해당 쿼리는 개체를 던져서 받음
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();

    }
}
