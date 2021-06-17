package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;
/*
    @Column(name = "TEAM_ID")
    private Long teanId;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public void changeTeam(Team team){ //set을 관례적으로 쓰느것보단 메서드를 만드는게 좋다(로직이들어가면)
        this.team=team;
        team.getMembers().add(this);
    }
}
