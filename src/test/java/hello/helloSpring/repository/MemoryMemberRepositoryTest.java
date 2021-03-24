package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    // 초록불 뜨던 findByName이 밑에서 findAll()을 작성하고 난 후에
    // 클래스 전체 테스트를 할 때 에러가 난 걸 확인할 수 있었는데
    // 그 이유는 findAll() 메소드가 먼저 실행되고 Member1, Member2객체가 이미 findByName()에서 생성이 되었으므로 에러가 뜬다.
    // 그래서 테스트코드(메소드)가 하나 실행되고 초기화시켜주기 위해서 위와 같이 @AfterEach 어노테이션과 repository를 초기화 하는 작업이 필요하다.
    // 테스트는 서로 순서가 없이 의존관계가 없이 실행되어야 한다. 공용데이터를 지워줘야 된다.
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        memoryMemberRepository.save(member);

        Member result = memoryMemberRepository.findById(member.getId()).get();
        // JUNIT
//        Assertions.assertEquals(member, result);
        // AssertJ
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memoryMemberRepository.save(member2);

        Member result = memoryMemberRepository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memoryMemberRepository.save(member2);

        List<Member> result = memoryMemberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
