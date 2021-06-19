package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @MappedSuperclass
 * 상속관계 매핑이 아님
 * 엔티티X, 테이블과 매핑X
 * 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공
 *
 * 기능:
 * 1.테이블과 관계 없고, 단순히 엔티티가 공통으로 사용하는 매핑 정보를 모으는 역할
 * 2.주로 등록일, 수정일,등록자.수정자 같은 전체 엔티티에서 공통으로 적용하는 정보를 모을 때 사용
 * 3.참고:@Entity 클래스는 엔티티나 @MappedSuperClass로 지정한 클래스만 상속 가능
 */
@Getter
@Setter
@MappedSuperclass //매핑정보만 받는 클래스(공통 속성을 사용하기위해)
public abstract class BaseEntity {

    @Column(name = "INSERT_MEMBER")
    private String createBy;
    private LocalDateTime createDate;
    @Column(name = "UPDATE_MEMBER")
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}
