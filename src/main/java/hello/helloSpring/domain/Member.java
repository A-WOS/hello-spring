package hello.helloSpring.domain;

import javax.persistence.*;

/**
 * JPA는 표준 인터페이스
 * JPA는 객체랑 ORM기술 ORM : Object, Relation Database, Mapping 을함.
 */
// JPA가 관리하는 엔티티가 되는 것
@Entity
public class Member {
    
    // DB가 알아서 아이디를 생성해주는거를 IDENTITY라고 함
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
