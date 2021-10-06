package kanubucks;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderDenied extends AbstractEvent {

    private Long id;

    public OrderDenied(){
        super();
    }
}
