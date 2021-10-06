package kanubucks;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class PurchaseRequested extends AbstractEvent {

    private Long id;
    private List<OrderItem> orderItems;
    private Long userId;
    private Date time;
    private Integer amount;
    private String status;
    private Integer couponNum;

}