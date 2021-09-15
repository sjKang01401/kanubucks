package kanubucks;

public class OrderRequested extends AbstractEvent {

    private Long id;
    private String userId;
    private Date time;
    private Integer amount;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCustomer() {
        return userId;
    }

    public void setCustomer(String userId) {
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