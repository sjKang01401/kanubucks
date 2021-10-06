package kanubucks;

import kanubucks.config.kafka.KafkaProcessor;
import kanubucks.external.Menu;
import kanubucks.external.MenuService;
import kanubucks.external.User;
import kanubucks.external.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusTableViewHandler {

    @Autowired
    private StatusTableRepository statusTableRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPurchaseRequested (@Payload PurchaseRequested purchaseRequested) {

        try {

            if (!purchaseRequested.validate()) return;

            // Order Unique 확인
            List<StatusTable> statusTableList = statusTableRepository.findByOrderId(purchaseRequested.getId());
            if (statusTableList != null && statusTableList.size() != 0) {
                return;
            }

            StatusTable statusTable = new StatusTable();

            // Order 정보
            statusTable.setOrderId(purchaseRequested.getId());

            List<OrderItem> orderItems = new ArrayList<>();
            for(OrderItem purchaseOrderItem : purchaseRequested.getOrderItems()) {

                OrderItem orderItem = new OrderItem();

                orderItem.setMenuId(purchaseOrderItem.getMenuId());
                orderItem.setQty(purchaseOrderItem.getQty());
                orderItem.setTemp(purchaseOrderItem.getTemp());
                orderItem.setOption(purchaseOrderItem.getOption());

                // Menu 정보
                Menu menu = StatusApplication.applicationContext.getBean(MenuService.class)
                                .getMenu(orderItem.getMenuId());
                orderItem.setMenuName(menu.getName());
                orderItem.setMenuPrice(menu.getPrice());

                orderItems.add(orderItem);
            }

            // User 정보
            statusTable.setUserId(purchaseRequested.getUserId());

            User user = StatusApplication.applicationContext.getBean(UserService.class)
                            .getUser(statusTable.getUserId());
            statusTable.setUserName(user.getName());
            statusTable.setUserPhone(user.getPhone());

            // 그밖의 정보
            statusTable.setTime(purchaseRequested.getTime());
            statusTable.setAmount(purchaseRequested.getAmount());
            statusTable.setCouponNum(purchaseRequested.getCouponNum());
            statusTable.setStatus(purchaseRequested.getStatus());

            // view 레파지 토리에 save
            statusTableRepository.save(statusTable);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderConfirmed(@Payload OrderConfirmed orderConfirmed) {

        try {

            if (!orderConfirmed.validate()) return;

            List<StatusTable> statusTableList = statusTableRepository.findByOrderId(orderConfirmed.getId());

            StatusTable statusTable = statusTableList.get(0);

            statusTable.setStatus(StatusTable.CONFIRMED);

            statusTableRepository.save(statusTable);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderDenied(@Payload OrderDenied orderDenied) {

        try {

            if (!orderDenied.validate()) return;

            List<StatusTable> statusTableList = statusTableRepository.findByOrderId(orderDenied.getId());

            StatusTable statusTable = statusTableList.get(0);

            statusTable.setStatus(StatusTable.DENIED);

            statusTableRepository.save(statusTable);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCompleted(@Payload OrderCompleted orderCompleted) {

        try {

            if (!orderCompleted.validate()) return;

            List<StatusTable> statusTableList = statusTableRepository.findByOrderId(orderCompleted.getId());

            StatusTable statusTable = statusTableList.get(0);

            statusTable.setStatus(StatusTable.COMPLETED);

            statusTableRepository.save(statusTable);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenTakeOutCompleted(@Payload TakeOutCompleted takeOutCompleted) {

        try {

            if (!takeOutCompleted.validate()) return;

            List<StatusTable> statusTableList = statusTableRepository.findByOrderId(takeOutCompleted.getId());

            StatusTable statusTable = statusTableList.get(0);

            statusTable.setStatus(StatusTable.TOOKOUT);

            statusTableRepository.save(statusTable);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

