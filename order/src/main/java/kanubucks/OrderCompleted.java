package kanubucks;

public class OrderCompleted extends AbstractEvent {

    private Long id;

    public OrderCompleted(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
