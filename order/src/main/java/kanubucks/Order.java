package kanubucks;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Order_table")
@Getter@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userId;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date time;
    private Integer amount;
    private String status;
    private Integer couponNum;

    @ElementCollection
    @CollectionTable(name = "Order_Item_table",
    joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> orderItems;

    public final static String REQUESTED = "requested";
    public final static String FAILED = "failed";
    public final static String CONFIRMED = "confirmed";
    public final static String DENIED = "denied";
    public final static String COMPLETED = "completed";
    public final static String TOOKOUT = "tookOut";

    @PostPersist
    public void onPostPersist(){
        OrderDenied orderDenied = new OrderDenied();
        BeanUtils.copyProperties(this, orderDenied);
        orderDenied.publishAfterCommit();

        OrderConfirmed orderConfirmed = new OrderConfirmed();
        BeanUtils.copyProperties(this, orderConfirmed);
        orderConfirmed.publishAfterCommit();

        OrderRequested orderRequested = new OrderRequested();
        BeanUtils.copyProperties(this, orderRequested);
        orderRequested.publishAfterCommit();

        OrderCompleted orderCompleted = new OrderCompleted();
        BeanUtils.copyProperties(this, orderCompleted);
        orderCompleted.publishAfterCommit();

        TakeOutCompleted takeOutCompleted = new TakeOutCompleted();
        BeanUtils.copyProperties(this, takeOutCompleted);
        takeOutCompleted.publishAfterCommit();

    }
    @PrePersist
    public void onPrePersist(){
        PurchaseRequested purchaseRequested = new PurchaseRequested();
        BeanUtils.copyProperties(this, purchaseRequested);
        purchaseRequested.publishAfterCommit();

    }



}