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
    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL, orphanRemoval = true)// orpan은 고아객체
    private List<Child> childList = new ArrayList<>();
    //사실상 CascadeType.ALL 삭제 수정 등 모든거에 영향줌
    //연관 관계 편의메서드
    public void addChild(Child child){
        childList.add(child);
        child.setParent(this);
    }
}
