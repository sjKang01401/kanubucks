package kanubucks;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderCompleted extends AbstractEvent {

    private Long id;

    public OrderCompleted(){
        super();
    }
}
