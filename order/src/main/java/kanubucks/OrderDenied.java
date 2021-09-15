package kanubucks;

public class OrderDenied extends AbstractEvent {

    private Long id;

    public OrderDenied(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
