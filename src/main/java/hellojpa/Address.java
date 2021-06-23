package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable //임베디드(값타입)는 불변 객체 설계 (생성자만 사용) setter은 절대 함부로 쓰면안됨
@Getter
@Setter
public class Address {
    private String city;
    private String street;
    private String zipcodes;

    //private Member member; 엔티티값도 쓸수있음
    public Address(){

    }

    public Address(String city, String street, String zipcodes) {
        this.city = city;
        this.street = street;
        this.zipcodes = zipcodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcodes, address.zipcodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcodes);
    }
}
