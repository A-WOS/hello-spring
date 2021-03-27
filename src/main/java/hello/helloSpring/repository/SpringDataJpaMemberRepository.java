package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPQL select m from Member m where m.name = ?
    // findBy~~

    /**
     * JpaRepository위에 PagingAndSortingRepository가 있고 그 위에 CrudRepository가 있다.
     * 해당 인터페이스에는 공통적으로 쓸 메소드들이 있지만
     * findByName과 같이 커스텀해야되는 메소드들도 있기 때문에 밑에와 같이 작성해준다.
     * findBy~~라는 규칙이 있기때문에 Name을 찾고싶으면 findByname, email을 찾고 싶으면 findByEmail 이런식으로 추가할 수 있다.
     *
     */
    @Override
    Optional<Member> findByName(String name);

}
