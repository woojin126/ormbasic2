package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Team {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;
/*
    CASE: N:1
    @OneToMany(mappedBy = "team")//나는 member.team에 연결되어있다. (디비에는 표시안하고 내부적으로만 연결하겟다)
    private List<Member> members = new ArrayList<>();//관례 초기화해둠 add할때  null이 안들어가게
*/
    //CASE: 1:N table don't touch
    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();

    /*
    CASE: N:1
    public void addMember(Member member){
        member.setTeam(this);
        members.add(member);
    }*/

}
