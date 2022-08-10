package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // (6-3) 스프링 컨테이너와 테스트를 같이 실행한다. 즉 실제 스프링을 띄워서 동작시켜 테스트한다.
@Transactional // (6-3) 트랜잭션 -> DB의 동작 개념. 데이터 변경 뒤 변경사항을 커밋해야 실제로 변경된다. 여기서는 테스트가 끝나고 커밋하는 코드가 없으므로 DB의 변경사항이 롤백된다.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;

    // (6-3) @Autowired -> 스프링에서 MemberRepository 구현체를 알아서 연결해 줌. (SpringConfig에 설정되어있음)
    @Autowired MemberRepository memberRepository;

    @Test
    //@Commit //(6-3) 테스트 실행 후 변경사항을 커밋한다. 즉 DB에 반영된다.
    void 회원가입() { // 테스트 코드는 실제 코드에 포함되지 않기 때문에, 가독성을 위해 한글로도 많이 선언함.
        // given
        Member member = new Member();
        member.setName("spring");
        
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