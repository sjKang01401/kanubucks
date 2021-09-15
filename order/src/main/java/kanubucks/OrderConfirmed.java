package kanubucks;

public class OrderConfirmed extends AbstractEvent {

    private Long id;

    public OrderConfirmed(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
