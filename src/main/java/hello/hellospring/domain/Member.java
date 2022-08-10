package hello.hellospring.domain;

import javax.persistence.*;

@Entity // JPA가 관리하는 엔티티.
public class Member {

    // (6-5) @Id: member 테이블의 PK. @GenerationType.IDENTITY: DB에서 id값을 자동 설정.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 시스템에서 사용하는 id

    // (6-5) @Column(name = "username") -> DB에서 열 이름이 username인 경우 코드의 name과 DB의 username을 매핑해줌.
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
