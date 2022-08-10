package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional // (6-4) 데이터를 저장할 땐 항상 트랜잭션이 있어야 한다.
public class MemberService {

    private final MemberRepository memberRepository;

    // (4-2)의존하는 객체를 생성자를 통해 전달하는 DI 기법을 생성자 주입 기법이라 한다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증. 중복 시 예외를 던진다.
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName()); // 가입하려는 멤버의 이름이 이미 존재하는지 확인
        result.ifPresent(m -> { // 만약 존재하면 예외를 던진다.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
