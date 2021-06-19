package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue(value = "A")//DTYPE으로 들어갈 이름을 지정가능 (자식은 VALUE 로설정)
public class Album extends Item{

    private String artist;
}
