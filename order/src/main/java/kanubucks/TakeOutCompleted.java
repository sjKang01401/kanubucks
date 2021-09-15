package kanubucks;

public class TakeOutCompleted extends AbstractEvent {

    private Long id;

    public TakeOutCompleted(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
