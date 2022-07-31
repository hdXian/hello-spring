package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {

    @Bean // 스프링 빈으로 등록. 싱글톤으로 관리된다.
    public MemberService memberService(){
        return new MemberService(memberRepository()); // 바로 아래 정의된 memberRepository()
    }

    @Bean // 스프링 빈으로 등록. 싱글톤으로 관리된다.
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
