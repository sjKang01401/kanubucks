package kanubucks;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Status_table")
@Getter @Setter
public class StatusTable {

        @Id @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;

        @Column(name = "order_id")
        private Long orderId;

        @ElementCollection
        @CollectionTable(name = "OrderItem_table", joinColumns = @JoinColumn(name = "order_id"))
        private List<OrderItem> orderItems;

        private Long userId;
        private String userName;
        private String userPhone;

        private Date time;
        private Integer amount;
        private Integer couponNum;
        private String status;
}