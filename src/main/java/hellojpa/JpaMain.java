package hellojpa;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@Slf4j
public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
      /*      Team team = new Team();
            team.setName("TeamA");
            em.persist(team); //pk값이 정해지고 영속상태가됨

            Member member = new Member();
            member.setUsername("memb er1");
            member.setTeam(team);//team에서 pk값을 꺼내 사용함
            //member.setTeamId(team.getId());//너무 객체지향적이지못하다,, setTeam을하면 좋을거 같은데(외래키식별자를 직접다루기떄문)
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("==========================");//출력이 안되는이유는 영속성 컨텍스트 1차캐시에서 바로가져옴(출력되는걸보고싶다? 위에줄확인)
            Member findMember = em.find(Member.class, member.getId()); //맴버 ->팀 양방향
            System.out.println("==========================");
            //Long findTeamId = findMember.getTeanId();
            Team findTeam = findMember.getTeam();
            //Team findTeam = em.find(Team.class, findTeamId);//맴버 번호에 해당하는 팀을 구하기위해 위와같은 과정을거쳐야함;; 너무힘들

            List<Member> members = findMember.getTeam().getMembers();//팀 -> 맴버 양방향

            for(Member m  : members){
                log.info("TeamInnerMember={}",m.getUsername());
            }

            //수정을하고싶다
            Team newTeam = em.find(Team.class,100L);
            findMember.setTeam(newTeam);
*/
           // 양방향 연관관계의 예제

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);
            member.changeTeam(team); //양쪽에 값이들어가기하기위한 기준을 Member에서 작성
            //team.addMember(member); //양쪽에 값이 들어가기 위한 기준을 team에서작성 해도됨

            //team.getMembers().add(member);//핵심 객체지향적으로하기위해 팀에도 맴버의값이 잇어야함

            Team findTeam = em.find(Team.class,team.getId());
            List<Member> members = findTeam.getMembers();
            for (Member m : members){
                System.out.println("TeamOfMember"+m);
            }

            Member findMember = em.find(Member.class,member.getId());
            System.out.println("getName"+findMember.getTeam().getName());



            tx.commit();



            
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }finally {
            em.close();//엔티티 매니저 종료
        }
    }
}
