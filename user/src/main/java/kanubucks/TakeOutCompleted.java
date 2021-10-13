package kanubucks;

public class TakeOutCompleted extends AbstractEvent {

    private Long id;
    private Long userId;
    private Integer qty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getQty() { return qty; }

    public void setQty(Integer qty) { this.qty = qty; }
}