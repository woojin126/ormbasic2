package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
/*extends BaseEntity*/
public class Member  {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

/*

    //기간 period
    @Embedded
    private Period workPeriod;
*/


    //주소
    @Embedded
    private Address homeAddress;

    /**
     * 값 타입 컬렉션
     * 1.값 타입을 하나 이상 저장할 때 사용
     * 2.@ElementCollection, @CollectionTable 사용
     * 3.데이터베이스는 컬렉션을 같은 테이블에 저장할 수 없음
     * 컬렉션을 저장하기 위한 별도의 테이블이 필요
     * 4.컬렉션은 기본적으로 지연로딩 사용!
     */
    @ElementCollection //값 타입 컬렉션입니다 
    @CollectionTable(name ="FAVORITE_FOOD",joinColumns = @JoinColumn(name = "MEMBER_ID")) //컬렉션 테이블 생성,외래키로지정 만들어질 테이블에
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();


    @ElementCollection
    @CollectionTable(name ="ADDRESS",joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();
    //위대신 아래 엔티티로 매핑 하자

  /*  @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID") //1대 다 단방향
    private List<AddressEntity> addressHistory = new ArrayList<>();*/


    /**
     * @AttributeOverride:속성 재정의
     * 1.한 엔티티에서 같은 값 타입을 사용하려면?
     * 2.컬럼 명이 중복됨
     * 3. @AttributeOverrides, @AttributeOverride 를 사용해서 컬럼 명 속성을 재정의
     */
  /*  @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city", column = @Column(name = "WORK_CITY")), //디비컬럼은 따로매핑
            @AttributeOverride(name="street", column = @Column(name = "WORD_STREET")),
            @AttributeOverride(name="zipcodes", column = @Column(name = "WORK_ZIPCODES"))
    })
    private Address workAddress;//같은 주소가있다면? 위에처럼 새로 오버라이드
*/
 /*   @ManyToOne//oneToMany쪽이 주인
    @JoinColumn(name = "TEAM_ID",insertable = false,updatable = false)
    private Team team;*/

/*    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;*/
/*
    @Column(name = "TEAM_ID")
    private Long teanId;*/

    /**
     * LAZY
     *1.지연로딩을 사용시 연관된 것을 프록시로 가져옴!
     *2.team.getName() // 실제 team을 사용하는 시점에 초기화(DB조회가 일어남)
     *3.Member와 Team을 따로 자주 사용하는경우( 맴버만 조회할 경우가 많은경우) 사용
     *4.@ManyToOne , @OneToOne은 기본이 즉시 로딩이므로 지연로딩으로 변경
     * 
     * EAGER
     *1.MEMBER와 Team을 자주 함께 사용할때. (함께조회하도록해버림) ==즉시로딩
     *2.엔 한방에 outerJoin 해서 다가져오기떄문에 proxy가 필요없음
     */



     //CASE: N:1
    @ManyToOne(fetch = FetchType.LAZY)//LAZY로 이렇게하면 proxy 객체만 조회 Member만 조회하겟다 || (지연로딩을 사용시 연관된 것을 프록시로 가져옴!)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void changeTeam(Team team){ //set을 관례적으로 쓰느것보단 메서드를 만드는게 좋다(로직이들어가면)
        this.team=team;
        team.getMembers().add(this);
    }


    /*Member Set*/

    /*
    M:N 다대다 관계 의 잘못된 예제
    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();
   */

/*    //M:N 다대다 관계의 옳바른 예제 ( 1:다 다:1로 풀어내버리기)

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();*/


/*    public void changeLocker(Locker locker){
        this.locker = locker;
        locker.setMember(this);
    }*/


}
