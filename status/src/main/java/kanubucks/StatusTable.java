package kanubucks;

import com.fasterxml.jackson.annotation.JsonFormat;
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

        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
        private Date time;
        private Integer amount;
        private Integer couponNum;
        private String status;

        public final static String REQUESTED = "requested";
        public final static String FAILED = "failed";
        public final static String CONFIRMED = "confirmed";
        public final static String DENIED = "denied";
        public final static String COMPLETED = "completed";
        public final static String TOOKOUT = "tookOut";
}