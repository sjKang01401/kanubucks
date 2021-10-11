package kanubucks;

import java.sql.Date;

public class OrderRequested extends AbstractEvent {

    private Long id;
    private String customer;
    private Date time;
    private Integer amount;
    private String status;
    private Integer qty;
    private Integer couponNum;

    public OrderRequested(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public Integer getQty() {
        return qty;
    }
    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getCouponNum() {
        return couponNum;
    }
    public void setCouponNum(Integer couponNum) {
        this.couponNum = couponNum;
    }
}
