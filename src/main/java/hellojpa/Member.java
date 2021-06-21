package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

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
     *2.Member와 Team을 따로 자주 사용하는경우( 맴버만 조회할 경우가 많은경우) 사용
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
