package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "ADDRESSTABLE")
public class AddressEntity { //엔 엔티티

    @Id @GeneratedValue
    private Long id;

    private Address address; //엔티티로 래핑 (엔 값타입)

    public AddressEntity(){

    }
    public AddressEntity(Address address) {
        this.address = address;
    }

    public AddressEntity(String city, String street, String zipcode) {
        this.address=new Address(city,street,zipcode);
    }
}
