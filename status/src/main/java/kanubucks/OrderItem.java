package kanubucks;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@Getter @Setter
public class OrderItem {

        private Long menuId;
        private String menuName;
        private String menuPrice;

        private Integer qty;
        private String temp;
        private String option;
}