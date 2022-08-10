package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // (6-4) JPA의 모든 기능은 EntityManager를 통해 동작한다.
    // 앱이 실행되면 스프링 부트에서 자동으로 EntityManager 객체를 생성하고, 관련된 기능을 수행한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // (6-4) DB에 member 데이터를 저장한다. ID 설정 및 DB insert까지 모두 실행된다.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // DB에 저장되는 Member 클래스 정보와 PK값(여기서는 id)를 전달하여 탐색.
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        // (6-4) 결과 리스트에서 조건에 만족하는 하나만 리턴.
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // (6-4) jpql을 전달하면 JPA가 알맞게 매핑하여 sql문으로 번역해 전달한다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
