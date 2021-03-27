package hello.helloSpring.service;

import hello.helloSpring.domain.Member;
import hello.helloSpring.repository.MemberRepository;
import hello.helloSpring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** @SpringBootTest 스프링 컨테이너와 테스트를 함께 실행한다.
 *  @Transactional db에 넣었던 값들이 테스트코드가 끝나면 롤백을 해서 디비에 반영이 안됨.
 *          테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고,
 *          테스트 완료후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
 *          테스트 케이스에서 '만' 테스트가 끝나면 롤백함.
 *
 *          스프링 컨테이너, db 연동 한거를 통합테스트라고 표현을 함.
 */
@SpringBootTest
@Transactional
// CTRL + SHIFT + T -> 해당 클래스 테스트코드 바로 만들어줌.
class MemberServiceInegrationTest {


    // test code에서는 필드 주입으로 받아와서 써도됨 테스트를 가지고 외부에서 쓸건 아니기 때문에
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
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
    }
}