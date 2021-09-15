package kanubucks;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.Date;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String userId;
    private Date time;
    private Integer amount;
    private String status;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}