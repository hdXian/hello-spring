package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 각 테스트 시작 전에 아래 코드를 실행한다.
    public void beforeEach() {
        // 각 테스트마다 MemberService와 그곳에서 쓰는 MemoryMemberRepository를 새로 생성.
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 각 테스트 메서드가 끝날때마다 아래의 함수를 실행한다.
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { // 테스트 코드는 실제 코드에 포함되지 않기 때문에, 가독성을 위해 한글로도 많이 선언함.
        // given
        Member member = new Member();
        member.setName("hello");
        
        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        // memberService.jong(member2)를 실행하면 IllegalStatcException을 throw하는지 테스트. throw한 예외를 반환한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // assertThrows(NullPointerException.class, () -> memberService.join(member2));

        /*
        try {
            memberService.join(member2);
            fail(); // catch문으로 넘어가지 않으면 fail() 호출.
        }catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123");
        }
        */

        // then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}