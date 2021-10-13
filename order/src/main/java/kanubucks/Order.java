package kanubucks;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Date;

@Entity
@Table(name="Order_table")
@Getter@Setter
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long userId;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date time;
    private Integer amount;
    private String status;
    private Integer couponNum;

    @ElementCollection
    @CollectionTable(name = "Order_Item_table",
    joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> orderItems;

    public final static String REQUESTED = "requested";
    public final static String FAILED = "failed";
    public final static String CONFIRMED = "confirmed";
    public final static String DENIED = "denied";
    public final static String COMPLETED = "completed";
    public final static String TOOKOUT = "tookOut";

    private final static String paymentSystemURL = "http://dummy.restapiexample.com/api/v1/create";

    @PostPersist
    public void onPostPersist(){
        if(this.status.equals(FAILED)) return;

        OrderRequested orderRequested = new OrderRequested();

        int qty = 0;
        if(orderItems!=null){
            for(OrderItem item: orderItems){
                qty+=item.getQty();
            }
        }

        orderRequested.setQty(qty);
        BeanUtils.copyProperties(this, orderRequested);
        orderRequested.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){
        switch (status){
            case FAILED:
                System.out.println("결제 실패로 인해 취소된 거래");
                break;
            case CONFIRMED:
                OrderConfirmed orderConfirmed = new OrderConfirmed();
                BeanUtils.copyProperties(this, orderConfirmed);
                orderConfirmed.publishAfterCommit();
                break;
            case DENIED:
                OrderDenied orderDenied = new OrderDenied();
                BeanUtils.copyProperties(this, orderDenied);
                orderDenied.publishAfterCommit();
                break;
            case COMPLETED:
                OrderCompleted orderCompleted = new OrderCompleted();
                BeanUtils.copyProperties(this, orderCompleted);
                orderCompleted.publishAfterCommit();
                break;
            case TOOKOUT:
                TakeOutCompleted takeOutCompleted = new TakeOutCompleted();
                BeanUtils.copyProperties(this, takeOutCompleted);
                takeOutCompleted.publishAfterCommit();
                break;
            default:
                break;
        }
    }

    @PrePersist
    public void onPrePersist(){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cardNum", "1234-5678-9012-3456");
        params.add("amount", Integer.toString(amount));

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        RestTemplate rt = new RestTemplate();

        try{
            ResponseEntity<String> response = rt.exchange(
                    paymentSystemURL, //{요청할 서버 주소}
                    HttpMethod.POST, //{요청할 방식}
                    entity, // {요청할 때 보낼 데이터}
                    String.class
            );
            if(response.getStatusCode().value() != 200){
                System.out.println("카드 인증 오류!!!");
                this.status = FAILED;
                return;
            }
        } catch(Exception e) {
            System.out.println("카드 인증 오류!!!");
            this.status = FAILED;
            return;
        }
        System.out.println("카드 정보 인증 완료");
    }
}