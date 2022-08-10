package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// (6-6) JpaRepository를 구현하면 자동으로 스프링 빈으로 등록됨.
public interface SpringJpaDataMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
                                            //   (6-6) JpaRepository<데이터 타입, PK 타입>

    // (6-6) JpaRepository에 기본적인 메서드들이 정의되어 있고, 구현체가 스프링 빈으로 등록될 때 함께 구현된다.
    // 그 외에 추가적인 기능은 규칙에 따라 메서드 이름을 정의해놓으면 함수가 오버라이딩된다.
    // 예시의 findByName()의 경우 select m from Member m where name=? 으로 자동으로 jpl이 생성되어 동작한다.
    @Override
    Optional<Member> findByName(String name);
}
