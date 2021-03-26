package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    // 동시성 문제때문에 Concurrent?HashMap을 써야되는데 예제이기 때문에 그냥 HashMap을 쓰도록함.
    private static Map<Long, Member> store = new HashMap<>();
    // 동시성 문제때문에 atumnLong 해야됨. 원래는
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

//        return store.get(id); 위와 같이 이런식으로 리턴해주면 값이 null일때 오류가 뜨기 때문에
//        Optional.ofNullable() <- 해당 메소드로 감싸주면 빨간줄도 안뜨고 좋음.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                // 파라미터로 들어온 name이 같은 name인지 확인.
                .filter(member -> member.getName().equals(name))
                // 찾으면 반환. 결과를 Optional로 반환.
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // store.values() = Member
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
