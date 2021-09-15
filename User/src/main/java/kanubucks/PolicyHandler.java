package kanubucks;

import kanubucks.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired UserRepository userRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverTakeOutCompleted_StampSaved(@Payload TakeOutCompleted takeOutCompleted){

        if(!takeOutCompleted.validate()) return;

        System.out.println("\n\n##### listener StampSaved : " + takeOutCompleted.toJson() + "\n\n");



        // Sample Logic //
        // User user = new User();
        // userRepository.save(user);

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderRequested_CouponUsed(@Payload OrderRequested orderRequested){

        if(!orderRequested.validate()) return;

        System.out.println("\n\n##### listener CouponUsed : " + orderRequested.toJson() + "\n\n");



        // Sample Logic //
        // User user = new User();
        // userRepository.save(user);

    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
