package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Team team = new Team();
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
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("==========================");
            //Long findTeamId = findMember.getTeanId();
            Team findTeam = findMember.getTeam();
            //Team findTeam = em.find(Team.class, findTeamId);//맴버 번호에 해당하는 팀을 구하기위해 위와같은 과정을거쳐야함;; 너무힘들
            tx.commit();

            //수정을하고싶다
            Team newTeam = em.find(Team.class,100L);
            findMember.setTeam(newTeam);
            
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        }finally {
            em.close();//엔티티 매니저 종료
        }
    }
}
