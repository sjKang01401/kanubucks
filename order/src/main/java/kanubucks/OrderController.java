package kanubucks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
 @RequestMapping("/orders")
 public class OrderController {

  @Autowired
  OrderRepository orderRepository;

  @RequestMapping(method = RequestMethod.GET, path = "/confirm/{id}")
  public String confirm(@PathVariable("id") long orderId){
   Optional<Order> orderOptional = orderRepository.findById(orderId);

   if (!orderOptional.isPresent()) {
    return "헤딩 ID에 해당하는 주문 없음";
   }

   Order order = orderOptional.get();

   if (!order.getStatus().equals(Order.REQUESTED)) {
    return "주문 확정이 불가능한 상태";
   }

   order.setStatus(Order.CONFIRMED);
   orderRepository.save(order);

   return "성공";
  }

 @RequestMapping(method = RequestMethod.GET, path = "/deny/{id}")
 public String deny(@PathVariable("id") long orderId){
  Optional<Order> orderOptional = orderRepository.findById(orderId);

  if (!orderOptional.isPresent()) {
   return "헤딩 ID에 해당하는 주문 없음";
  }

  Order order = orderOptional.get();

  if (!order.getStatus().equals(Order.REQUESTED)) {
   return "주문 거절이 불가능한 상태";
  }

  order.setStatus(Order.DENIED);
  orderRepository.save(order);

  return "성공";
 }


 @RequestMapping(method = RequestMethod.GET, path = "/complete/{id}")
 public String complete(@PathVariable("id") long orderId){
  Optional<Order> orderOptional = orderRepository.findById(orderId);

  if (!orderOptional.isPresent()) {
   return "헤딩 ID에 해당하는 주문 없음";
  }

  Order order = orderOptional.get();

  if (!order.getStatus().equals(Order.CONFIRMED)) {
   return "주문 완료가 불가능한 상태";
  }

  order.setStatus(Order.COMPLETED);
  orderRepository.save(order);

  return "성공";
 }

 @RequestMapping(method = RequestMethod.GET, path = "/tookout/{id}")
 public String tookOut(@PathVariable("id") long orderId){
  Optional<Order> orderOptional = orderRepository.findById(orderId);

  if (!orderOptional.isPresent()) {
   return "헤딩 ID에 해당하는 주문 없음";
  }

  Order order = orderOptional.get();

  if (!order.getStatus().equals(Order.COMPLETED)) {
   return "수취가 불가능한 상태";
  }

  order.setStatus(Order.TOOKOUT);
  orderRepository.save(order);

  return "성공";
 }

 @RequestMapping(method = RequestMethod.POST, path = "/purchase")
 public String purchase(@RequestBody Order order){
   return order.toString();

 }

}