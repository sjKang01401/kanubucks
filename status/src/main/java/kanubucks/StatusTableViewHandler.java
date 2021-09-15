package kanubucks;

import kanubucks.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class StatusTableViewHandler {


    @Autowired
    private StatusTableRepository statusTableRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPurchaseRequested_then_CREATE_1 (@Payload PurchaseRequested purchaseRequested) {
        try {

            if (!purchaseRequested.validate()) return;

            // view 객체 생성
            StatusTable statusTable = new StatusTable();
            // view 객체에 이벤트의 Value 를 set 함
            statusTable.setOrderId(purchaseRequested.getId());
            // view 레파지 토리에 save
            statusTableRepository.save(statusTable);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderConfirmed_then_UPDATE_1(@Payload OrderConfirmed orderConfirmed) {
        try {
            if (!orderConfirmed.validate()) return;
            // view 객체 조회

            List<StatusTable> statusTableList = statusTableRepository.findByOrderId(orderConfirmed.getId());
            for(StatusTable statusTable : statusTableList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                statusTable.setOrderId(orderConfirmed.getId());
                // view 레파지 토리에 save
                statusTableRepository.save(statusTable);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

