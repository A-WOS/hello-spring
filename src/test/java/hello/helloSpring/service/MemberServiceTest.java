package hello.helloSpring.service;

import hello.helloSpring.domain.Member;
import hello.helloSpring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
// CTRL + SHIFT + T -> 해당 클래스 테스트코드 바로 만들어줌.

/**
 *  이런거를 단위테스트라고 함 순수하게 자바 코드로만 테스트하는거 최소한의 단위로 하는것.
 *  순순한 단위 테스트가 훨씬 좋은 테스트일 확률이 높다. (시간이 별로 안걸림)
 */
class MemberServiceTest {

//    MemberService memberService = new MemberService(memberRepository);
    MemberService memberService;
    // 현재 다른 MemmoryMemberRepository가 사용되고 있다. 그래서 같은 인스턴스를 쓰려면 MemberService에서
    // private final MemberRepository를 선언하고 외부에서 repository를 넣어주도록 설계.
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemoryMemberRepository memberRepository;

    // dependency Injection (DI)
    // 같은 인스턴스를 사용하도록 만들어줌.
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 테스트코드를 돌떄마다 db의 값을 다 날려줌.
    // 해당 @AfterEach와 동일한 기능을 하는게 MemberServiceIntegraionTest에서의 애노테이션인 @Transactional
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    // 테스트코드에서는 한글로 써도 됨. 직관적으로 알아보기 좋음.
    void 회원가입() {
        // given 뭔가가 주어졌는데
        Member member = new Member();
        member.setName("hello");

        // when 이걸 실행했을때
        Long saveId = memberService.join(member);

        // then 결과가 이게 나와야됨.
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
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. ");
//        }



        // then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}