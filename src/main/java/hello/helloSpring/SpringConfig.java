package hello.helloSpring;

import hello.helloSpring.repository.JpaMemberRepository;
import hello.helloSpring.repository.MemberRepository;
import hello.helloSpring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    @Autowired private DataSource dataSource;
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//    위의 필드와 생성자는 JdbcTemplateMemberRepository까지만 적용이됨.
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
//    위의 필드와 생성자는 JpaMemberRepository에서 사용.

    /**
     * 컴포넌트 스캔 방식 
     * Bean 주입 방식
     *  장점 : 나중에 메모리 리포지토리를 다른 리포지토리로 변경할 때 개편함
     *  return new MemoryMemberRepository()에서 return new DbMemberRepository()로 변경하면됨 
     *  해당 리포지토리 구현체를 바꿔야 하는데 다른 코드 다른 클래스 건들 필요없이 딱 리포지토리 하나만 바꿔치기 할 수 있음
     */
    @Bean
    public MemberService memberService() {

//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
