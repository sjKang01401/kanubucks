package kanubucks;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TakeOutCompleted extends AbstractEvent {

    private Long id;

    public TakeOutCompleted(){
        super();
    }
}
