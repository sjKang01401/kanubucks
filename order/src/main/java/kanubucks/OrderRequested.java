package kanubucks;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class OrderRequested extends AbstractEvent {

    private Long id;
    private Long userId;
    private Date time;
    private Integer amount;
    private String status;
    private Integer qty;
    private Integer couponNum;
    private List<OrderItem> orderItems;

    public OrderRequested(){
        super();
    }

}
