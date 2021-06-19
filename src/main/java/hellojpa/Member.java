package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
/*
    @Column(name = "TEAM_ID")
    private Long teanId;*/

   /*
     CASE: N:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void changeTeam(Team team){ //set을 관례적으로 쓰느것보단 메서드를 만드는게 좋다(로직이들어가면)
        this.team=team;
        team.getMembers().add(this);
    }*/


    /*Member Set*/

    /*M:N 다대다 관계 의 잘못된 예제*/
  /*  @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();*/

    //M:N 다대다 관계의 옳바른 예제 ( 1:다 다:1로 풀어내버리기)

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();


    public void changeLocker(Locker locker){
        this.locker = locker;
        locker.setMember(this);
    }
}
