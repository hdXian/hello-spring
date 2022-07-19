package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 리포지토리에 Member 저장
    Optional<Member> findById(Long id); // id로 Member 검색
    Optional<Member> findByName(String name); // name으로 Member 검색
    List<Member> findAll(); // 리포지토리의 모든 Member를 List로 담아 반환.
}
