package hellojpa;

import lombok.extern.slf4j.Slf4j;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/*ormbasic2 깃허브연결*/
/*
깃 이그노어 동작하지않을때 대처
git rm -r --cached .
git add .
*/
@Slf4j
public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
      /*    N : 1
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

   /*        // 양방향 연관관계의 예제 N:1

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
            System.out.println("getName"+findMember.getTeam().getName());*/

         /*   //1 : N 단방향 관계

            Member member = new Member();
            member.setUsername("member1");

            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            team.getMembers().add(member); //이로직때문에 update 쿼리가 한번더나감 문제1
                                            //1:N의 엄청 복잡해짐 테이블이 많아질수록 문제2
            em.persist(team);*/


          /*
           1:1 관계 매핑
           Locker locker = new Locker();
            locker.setName("사물함1");
            em.persist(locker);

            Locker lockers = em.find(Locker.class, locker.getId());



            Member member = new Member();
            member.setUsername("사용자1");
            member.changeLocker(locker);
            log.info("memberValue={},",member.getLocker());
            log.info("lockerValue={}",lockers.getMember());
            em.persist(member);
*/

          /*  //상속관계 전략마다 테이블 생성, 관계 방식
            Movie movie = new Movie();
            movie.setDirector("AAAA");
            movie.setActor("BBBB");
            movie.setName("바람과함께 사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();  //디비에 이시점에 다넣음 1차캐시남음
            em.clear(); //영속성컨텍스트에 db를 깔끔히정리

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMove = " + findMovie); //find 하려면 조인해서 가져옴*/
/*
            //@MappedSuperclass
            Member member = new Member();
            member.setUsername("user1");
            member.setCreateBy("kim");
            member.setCreateDate(LocalDateTime.now());

            em.persist(member);

            //em.find(BaseEntity.class)//이런거안됨

            em.flush();
            em.clear();*/

            /**
             * 프록시 특징
             * 1.실제 Entity 클래스를 상속받아서사용
             * 2.실제 클래스와 겉 모양이 같다
             * 3.사용하는 입장에서는 진짜 객체인지 프록시 객체인지 구분하지 않고
             * 사용하면 됨 (이론상)
             */
            /**
             * 프록시 특징2
             * 1.프록시 객체는 처음 사용할 때 한 번만 초기화
             * 2.프록시 개체를 초기화 할 때, 프록시 객체가 실제 엔티티로 바뀌는 것은 아니다,
             * 초기화되면 프록시 객체를 통해서 실제 엔티티에 접근 가능
             * 3.프록시 객체는 원본 엔티티를 상속받음, 따라서 타입 체크시 주의해야함( == 비교실패,
             * 대신 instance of를 사용해야함)
             */
          /*  //프록시 확인
            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();


           // Member findMember = em.find(Member.class, member.getId());
            Member findMember = em.getReference(Member.class,member.getId());//값이 사용되는시점에 쿼리가 나감 (getUserName)부터 ,getId는 이미 인자로 사용해서
            //디비에서 안가져와도 알수있으니 getId에서는 실행안함 (프록시사용)//프록시 가짜클래스
            System.out.println("findMember = " + findMember.getClass());
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.getUsername() = " + findMember.getUsername());
            System.out.println("findMember.getUsername() = " + findMember.getUsername());//여기서는 sql 생성안됨 위에서이미해서
*/

            /**
             *   ==을 사용하면안되는 이유 써줌 //JPA 는 같은 트랜잭션 내에서 수행되는 값은 항상 == 를 충족시켜야함 그런데 reference(),find() 를  == 비교하면 false
             *             가뜸 instanceOf로 비교하자 프록시 객체는
             */
            /*
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member");
            em.persist(member);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);


            em.flush();
            em.clear();
           /*
            Member m = em.find(Member.class, member.getId());
            //Member m1 = em.find(Member.class, member1.getId());
            Member m1 = em.getReference(Member.class, member1.getId());

            System.out.println("findMember.username = "+ m1.getUsername());
            System.out.println("m1 == m2" + (m1.getClass() == m.getClass()));//find == find true ,find == getreference false
            System.out.println("m1 == m2" + (m instanceof Member));//이런식으로 써야함
            System.out.println("m1 == m2" + (m1 instanceof Member));//이런식으로 써야함*/

           

            /*System.out.println("=======================================");
            //detach 영속 상태에서 제외 시켜보기 프록시가 동작하는지 않하는지
            Member detatchMember = em.getReference(Member.class,member.getId());
            System.out.println("refMember = " + detatchMember.getClass());//proxy

            em.detach(detatchMember);//프록시는 데이터요청을 영속성 컨텍스트를 통해서하는데 제외를 시켜버렸다. 그러면 아래 값이 호출될까?
            //em.close();
            detatchMember.getUsername();
            //JPA 는 같은 트랜잭션 내에서 수행되는 값은 항상 == 를 충족시켜야함

            */
/*

            System.out.println("===============================================");

            Member refMember = em.getReference(Member.class,member1.getId());
            System.out.println("refMember = " + refMember.getClass());
            refMember.getUsername(); //초기화
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));//프록시 초기화 여부확인
*/

            /**
             *  //Member클래스  @ManyToOne(fetch = FetchType.LAZY) 사용시 결과확인
             */
   /*
            Member m = em.find(Member.class,member1.getId());

            System.out.println("m = " + m.getTeam().getClass());//LAZY로인해 팀은 proxy로 조회가됫음(지연로딩을 사용시 연관된 것을 프록시로 가져옴!)

            System.out.println("=============");
            m.getTeam().getName(); //프록시는 값을 사용하는시점에 초기화되면서 디비에 쿼리가나감!
            System.out.println("=============");*/


            /**
             * FetchType.EAGER의 문제점
             * JPQL 사용할때 EAGER 로되있으면 쿼리가 한번더나감;
             */
     /*       List<Member> members = em.createQuery("select m from Member m join fetch m.team",Member.class)
                    .getResultList();
            
            //SQL: select * from Member // 이렇게 쿼리가 나가겠지?  그런데 값을 반환하고보니 EAGER? 팀값도 같이 가져와야하네? 다시 반복
            //SQL: select * from Team where TEAM_ID = XXX*/

            /**
             * CASCADE 영속성전이
             * 연관 관게와는 관련 x
             * parent를 persist 할떄 그자식들도 모두 persist 를 해줄거야 라는것,
             * 
             * tip)
             * 1.반드시 하나의부모가 자식들을 관리할때 써야함 (다른부모가 같은 자식을 관리하면 절대 x) == 소유자가 하나일떄만 쓰자
             * 2.부모와 자식의 life Cycle이 유사할때 쓰는게좋음( 등록이나,삭제,등등)
             */


            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent); //코드가너무많아 parent 를 persist할때 child도 같이 persist햇으면 좋겠따
            //em.persist(child1);
           // em.persist(child2);

            em.flush();
            em.clear();


            /**
             * 고아객체 삭제  orphanRemoval = true
             * 
             * 1.참조하는 곳이 하나 일때 사용해야함!
             * 2.특정 엔티티가 (부모가) 개인 소유할 때 사용
             * @OneToOne  @OneToMany만 사용
             */
            Parent findParent =  em.find(Parent.class,parent.getId());
            findParent.getChildList().remove(0);


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close();//엔티티 매니저 종료
        }
    }
}
