package kanubucks;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TakeOutCompleted extends AbstractEvent {

    private Long id;
    private Integer qty;

    public TakeOutCompleted(){
        super();
    }
}
