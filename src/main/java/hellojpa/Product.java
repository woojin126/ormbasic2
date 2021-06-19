package hellojpa;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;



/*
잘못된 M:N 다대다 예제
    @ManyToMany(mappedBy = "products")
    private List<Member> members = new ArrayList<>();

*/

    //옳바른 다대다 예제
    @OneToMany(mappedBy = "product")
    private List<MemberProduct> memberProducts = new ArrayList<>();
}
