package kanubucks;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
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
