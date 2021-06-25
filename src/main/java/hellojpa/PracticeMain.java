package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class PracticeMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Address address = new Address();
            address.setCity("서울");
            address.setStreet("영동로");
            address.setZipcodes("10000");

            Member member = new Member();
            member.setUsername("김우진");
            member.setHomeAddress(address);


            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("라면");

    /*
            member.getAddressHistory().add(new Address("old1","street","zipcode"));
            member.getAddressHistory().add(new Address("old2","street","zipcode"));
    */

            member.getAddressHistory().add(new AddressEntity("old1", "street", "zipcode"));
            member.getAddressHistory().add(new AddressEntity("old2","street","zipcode"));

            em.persist(member);

            em.flush();
            em.clear();

           // Member members = em.find(Member.class,1L);
            //members.getAddressHistory().remove(1);

           // em.flush();
            //em.clear();

           /* AddressEntity addressEntity = new AddressEntity();
            AddressEntity addressEntity1 = em.find(AddressEntity.class, 2L);
            em.remove(addressEntity1);
*/





            //homeCity -> newCity
           // findMember.getHomeAddress().setCity("newCity"); //큰일남 이뮤터블해야함



            /**
             *  값타입은 인스턴스자체를 갈아 끼워야해
             *  1. 임베디드 값 타입 수정
             */
             //Address a = findMember.getHomeAddress();
             //findMember.setHomeAddress(new Address("newCity",a.getStreet(),a.getZipcodes()));


            /**
             * 치킨 -> 한식
             * 값타입은 업데이트 자체를 할수도없고 하면안되.
             * 컬레션 값만 바꿔도 jpa가 뭐가 변경됬는지알고 쿼리를 날려줌(마치 영속성 전이 + organ 처럼)
             * Member에 모두의존
             * 2. 기본값 타입 컬렉션 수정
             *
             * 값 타입 컬렉션을 변경했을 때 JPA 구현체들은 테이블의 기본 키를 식별해서 변경된 내용만 반영하려고 노력합니다.
             * 하지만 사용하는 컬렉션이나 여러 조건에 따라 기본 키를 식별할 수도 있고, 식별하지 못할 수 도 있습니다.
             * 따라서 값 타입 컬렉션을 사용할 때는 모두 삭제하고 다시 저장하는 최악의 시나리오를 고려하면서 사용해야 합니다.
             * 값 타입 컬렉션의 최적화는 구현체마다 다릅니다.
             */

            Member findMember = em.find(Member.class,member.getId());
            findMember.getFavoriteFoods().remove("치킨");
             findMember.getFavoriteFoods().add("한식");

            /**
             * 3. 임베디드 값 타입 컬렉션 수정
             * 추적이 불가능 하기때문에 다지웠다가 생성
             * *참고*
             *
             * 값 타입 컬렉션은 영속성 전이(Cascade) + 고아 객체 제거(ORPHAN REMOVE) 기능을 필수로 가짐
             */
             //컬렉션은 대상으 찾을때 .eqauls() 를 사용한다.
             //findMember.getAddressHistory().remove(new Address("old1","street","zipcode"));
             //findMember.getAddressHistory().add(new Address("newCity1","street","zipcode"));


            /**
             * 위의 Set , List 차이
             * Set<String>은 하나의 필드만 집어서 삭제가능 하지만
             * List<객체타입> 은 수정하기위해서는 모든 필드를 삭제하고 다시생성 해야하는 이유
             *
             * . Set이라는 자료구조 자체가 유일성을 보장하기 때문에 @ElementCollection으로 생성되는 모든 필드를 PK로 잡으면 PK에 딱 맞게 최적화가 가능합니다.
             * 반면에 List 자료구조의 경우 최적화가 되지 않는데, List는 내부에 순서(index)가 있기 때문입니다.
             * 따라서 모두 지우고 다시 시작합니다. List의 경우 @OrderColumn이라는 애노테이션을 사용해서, 순서 컬럼을 추가한 다음에, 순서 컬럼을 PK에 추가하면 최적화가 가능해집니다.
             * 이 @OrderColumn을 추가하면 PK를 잡을 때 기본으로 id + order 컬럼을 PK로 잡습니다. 이렇게 하면 결과적으로 PK를 명확하게 구분할 수 있기 때문에 최적화가 가능합니다.
             */

            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
        }
        }


}
