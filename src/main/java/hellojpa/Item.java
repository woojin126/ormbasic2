package hellojpa;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**조인 전략 (비지니스적으로 중요하면 이전략)
 *--장점
 *테이블 정규화, 외래 키 참조 무결성 제약조건 활용가능
 *저장공간 효율화

 *--단점
 * 조회시 조인을 많이 사용, 성능 저하
 * 조회 쿼리가 복잡
 * 데이터 저장시 insert sql 2번 호출

 **단일 테이블 전략 (너무단순하고 확장 가능성이 없을거같을땐 그냥 단일테이블)
 * --장점
 * 조인이 필요 없으므로 일반적으로 조회 성능이 빠름
 * 조회 쿼리가 단순함
 *
 * --단점
 * 자식 엔티티가 매핑한 컬럼은 모두 NULL 허용해줘야함(데이터 무결성입장에선 애매함)
 * 단일 테이블에 모든것을 저장하므로 테이블이 커질 수 있고,
 * 상황에 따라서 조회 성능이 오히려 느려질수있다

 ** 구현 클래스마다 테이블전략
 * 이건그냥 쓰지마
 * EX) 정산 같은거할때 미침 각테이블 다 조회 하면서 해야함
 * 
 * 여러 자식테이블을 함께 조회할 때 성능이 느림(UNION SQL)을날려야함
 * 자식 테이블을 통합해서 쿼리하기 어려움 (여러개의테이블을 일일히 합쳐줘야함;)
 * */


@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)//join stategy,basic = single_table 상속사용시 방법 3가지
@DiscriminatorColumn//이걸 쓰면 DTYPE이생기고 앨범 .무비. 북 이름이 자동으로 들어감
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
