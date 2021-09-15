package kanubucks;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="StatusTable_table")
public class StatusTable {

        private Long id;
        private Long orderId;
        private Long userId;
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Integer amount;
        private String status;
        private Date time;


        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }
        public Long getOrderId() {
                return orderId;
        }

        public void setOrderId(Long orderId) {
                this.orderId = orderId;
        }
        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }
        public Integer getAmount() {
                return amount;
        }

        public void setAmount(Integer amount) {
                this.amount = amount;
        }
        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }
        public Date getTime() {
                return time;
        }

        public void setTime(Date time) {
                this.time = time;
        }

}