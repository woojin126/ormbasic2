package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Parent {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    /**
     * CascadeType.ALL + orphanRemovel =true
     * 1.스스로 생명주기를 관리하는 엔티티는 em.persist()로 영속화, em.remove()로제거
     * 2.두 옵션을 모두 활성화 하면 부모 엔티티를 통해서 자식의 생명
     * 주기를 관리할 수 있음.
     * 3. 도메인 주도 설계 (DDD)의 Aggregate Root 개념을 구현할 때 유용
     */

    /**
     * 이해하기 위한 ex)
     * CascadeType.Remove 와 orPhanRemoval = true 의 차이점은 관계가 끊어졌을 때 데이터에 대한 동작의 차이이다.
     * orPhanRemoval 는 부모 객체 (Member)와 자식 객체 (MemberFollow) 의 관계가 끊어졌을 때,
     * 자식 객체의 데이터까지 삭제해주는 역할을 하고,
     * CascadeType.Remove 는 참조를 변경시켜 무결성 오류를 안나게 할 뿐, 그 데이터는 남겨두게 된다.
     * 따라서 두 엔티티의 관계를 끊을 때, MemberFollow 테이블의 데이터가 계속 남아있기를 바란다면,
     * CascadeType.Remove 만 쓰는 것이고,
     * MemberFollow 테이블의 해당 데이터까지 삭제를 바란다면 orPhanRemoval = true 를 사용하는 것이다.
     */
    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL,orphanRemoval = true)// orpan은 고아객체
    private List<Child> childList = new ArrayList<>();
    //사실상 CascadeType.ALL 삭제 수정 등 모든거에 영향줌
    //연관 관계 편의메서드
    public void addChild(Child child){
        childList.add(child);
        child.setParent(this);
    }
}
