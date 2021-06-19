# ormbasic2

## ORM N:1 1:N 1:1 N:M 관계 사용해보기


##### Member 와 Team은  N:1 단방향,양방향 관계 연습
(테이블은 Join 으로 양쪽다 참조가가능하지만 객체는 만들어줘야함)

```java
객체에서 Team도 Member값을 가질수있도록 (양방향 가능하도록 추가)
public void changeTeam(Team team){ //set을 관례적으로 쓰느것보단 메서드를 만드는게 좋다(로직이들어가면)
        this.team=team;
        team.getMembers().add(this);
    }*/


```
##### Member와 Locker 은 1:1 단방향, 양방향 관계 연습
(1:1관계는 둘중하나에 아무곳에나 외래키 설정을 하면되고, Unique 제약조건이필요함)

```java
단방향은 아래하나
Member Entity
@OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
    
 양방향은 추가
 Locker Entity
 @OneToOne(mappedBy = "locker")
    private Member member;
```

##### 1:N은 단방향 양방향 되도록이면 사용하지 않는게 좋다 (야매방법)
(테이블은 무조건 외래키가 N(다) 쪽에 걸리게되는데 객체에서 1쪽에 외래키를 걸어버리는순간 패러다임의 불일치 **update쿼리를 한번더 날려야함
Team Entity에 member 객체를 추가해야하는데 Team 테이블에는 Member 컬럼이없음, 그래서 옆테이블인 Member에서 조회를 해와야함)
```java 

Team Entity 
  //CASE: 1:N table don't touch 단방향
    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();


Member Entity
  
  
```

``` java 
다:다는 사용안하는게좋음 ..
1:다 다:1 로풀어내서 사용하자

```

##### N:M 다대다 관계 단방향 (아예 쓰면안되욤) 한계가 명확함

실무에서 사용 할수가없어 단순하게 연결만하고 끝난다? 말도안된다.
ex) 중간테이블 Member_Product에 기본키 외래키 연결키만 딱 들어갈수있어 (추가가 더이상안되,쿼리도이상하게나감) 

```java
MemberEntity
*M:N 다대다 관계 
    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT")
    private List<Product> products = new ArrayList<>();


Product Entity생성 
   @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    //양방향을 원한다면 추가
    @ManyToMany(mappedBy = "products")
    private List<Member> members = new ArrayList<>();

```
