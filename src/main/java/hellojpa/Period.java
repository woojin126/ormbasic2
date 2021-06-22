package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable//값타입이라는 의미
@Getter
@Setter
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    public Period(){

    }

    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //여기서 메서드작정해도됨

}
