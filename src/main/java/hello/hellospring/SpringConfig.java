package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {

    /* (6-3), (6-4) JDBC 기반 리포지토리에서 사용.
    private DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */

    // @Autowired DataSource dataSource;

    /* (6-5) JPA 기반 리포지토리에서 사용.
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    */

    private final MemberRepository memberRepository;

    // (6-6) JpaRepository를 구현한 SpringJpaDataMemberRepository의 구현체가 스프링 빈으로 등록되어 인젝션된다.
    @Autowired // (6-6) @Autowired 생략가능.
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean // (4-2) 스프링 빈으로 등록. 싱글톤으로 관리된다.
    public MemberService memberService(){
        return new MemberService(memberRepository); // 바로 아래 정의된 memberRepository()
    }

    /*
    @Bean // (4-2) 스프링 빈으로 등록. 싱글톤으로 관리된다.
    public MemberRepository memberRepository() {
        // return new MemoryMemberRepository();
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        // return new JpaMemberRepository(em);
    }
    */
}
