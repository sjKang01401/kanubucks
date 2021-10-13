package kanubucks;

import kanubucks.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired UserRepository userRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTakeOutCompleted_StampSaved(@Payload TakeOutCompleted takeOutCompleted) {

        if (!takeOutCompleted.validate()) return;

        System.out.println("\n\n##### listener StampSaved : " + takeOutCompleted.toJson() + "\n\n");
        //Long userId = takeOutCompleted.getId();

        Long userId = takeOutCompleted.getId();
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.get();
        Integer stampCnt = user.getStamp();
        Integer orderQty = takeOutCompleted.getAmount();// 주문된 음료 수량 orderRequested.getOrderQty();
        stampCnt = stampCnt + orderQty;

        user.setStamp(stampCnt);
        userRepository.save(user);

    }

    @StreamListener(KafkaProcessor.INPUT)
    public String wheneverOrderRequested_CouponUsed(@Payload OrderRequested orderRequested){

        if(!orderRequested.validate()) return "";

        System.out.println("\n\n##### listener CouponUsed : " + orderRequested.toJson() + "\n\n");

        String userId = orderRequested.getCustomer();

        Optional<User> userOptional = userRepository.findById(orderRequested.getId());
        User user= userOptional.get();
        Integer stampCnt = user.getStamp();
        Integer  couponQty = orderRequested.getCouponQty();

        //사용자 Stamp수량 차감을 위해 쿠폰수량 * 10;
        Integer  useStampQty = couponQty * 10;

        //사용자 스탬프 수가 10장이 안될 경우
        if(stampCnt < 10) {
            return "사용 가능한 쿠폰이 없습니다.";
        //보유한 스탬프 수량보다 사용하려는 쿠폰 장수가 더 많을 경우
        } else if(stampCnt < 0) {
            return "잘못된 쿠폰 수량이 입력 되었습니다";
        }
        //Stamp 수량 차감
        stampCnt = stampCnt - useStampQty;

        user.setStamp(stampCnt);
        userRepository.save(user);

        return "";
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
