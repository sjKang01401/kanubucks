package kanubucks;


import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@ToString
public class OrderItem implements Serializable {

    @Column(name = "MENU_ID")
    private Long menuId;
    @Column(name = "QTY")
    private Integer qty;
    @Column(name = "TEMP")
    private String temp;
    @Column(name = "OPTION")
    private String option;
}
