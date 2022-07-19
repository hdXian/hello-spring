package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { // class에서 실행하면 작성된 모든 테스트 케이스를 테스트할 수 있다. 패키지 범위로도 가능하다.

    // 클래스 단위의 테스트에서 각 메서드의 실행 순서는 보장하지 않는다. 세 메서드에서 repository를 공유하고 있기 때문에 테스트가 실패한다.
    // 이러한 현상을 방지하기 위해 각 테스트 메서드가 실행된 후 repository를 비워줘야 할 필요가 있다. 그러한 초기화 동작을 AfterEach가 담당한다.
    // 즉, 궁극적으로 각 테스트 케이스는 서로 의존성이나 연관성이 없도록 설계해야 한다.
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각 테스트 메서드가 끝날때마다 아래의 함수를 실행한다.
    public void afterEach() {
        repository.clearStore();
    }

    @Test // JUnit이 테스트해준다.
    public void save(){
        Member member = new Member();
        member.setName("spring");


        repository.save(member);

        Member res = repository.findById(member.getId()).get(); // findById는 Optional<Member>를 리턴. Optional 객체의 get() 호출하여 Member 객체 획득.
        // System.out.println("res = " + (res == member));

        // JUnit에서 제공하는 Assertions
        // Assertions.assertEquals(res, member); // repository에 저장된 memeber가(res가) 처음에 저장하려 했던 member와 같은지 테스트.
        // Assertions.assertEquals(res, null); // res가 null과 다르면 오류 메시지 출력.

        // assertj에서 제공하는 Assertions
        assertThat(member).isEqualTo(res); // member가 res와 같은지 테스트.
        // assertThat(member).isEqualTo(null); // 다르면 오류메시지 출력.

    }

    @Test
    public void findByName() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member res = repository.findByName("spring1").get();

        assertThat(res).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> res = repository.findAll();

        assertThat(res.size()).isEqualTo(2);

    }

}
