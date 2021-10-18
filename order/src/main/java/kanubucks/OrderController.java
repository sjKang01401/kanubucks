package kanubucks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> confirm(@PathVariable("id") long orderId) {
		Optional<Order> orderOptional = orderRepository.findById(orderId);

		if (!orderOptional.isPresent()) {
			return new ResponseEntity<>("해당 ID에 해당하는 주문 없음", HttpStatus.BAD_REQUEST);
		}

		Order order = orderOptional.get();

		if (!order.getStatus().equals(Order.REQUESTED)) {
			return new ResponseEntity<>("주문 확정이 불가능한 상태", HttpStatus.BAD_REQUEST);
		}

		order.setStatus(Order.CONFIRMED);
		orderRepository.save(order);

		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/deny/{id}")
	public ResponseEntity<?> deny(@PathVariable("id") long orderId) {
		Optional<Order> orderOptional = orderRepository.findById(orderId);

		if (!orderOptional.isPresent()) {
			return new ResponseEntity<>("해당 ID에 해당하는 주문 없음", HttpStatus.BAD_REQUEST);
		}

		Order order = orderOptional.get();

		if (!order.getStatus().equals(Order.REQUESTED)) {
			return new ResponseEntity<>("주문 거절이 불가능한 상태", HttpStatus.BAD_REQUEST);
		}

		order.setStatus(Order.DENIED);
		orderRepository.save(order);

		return new ResponseEntity<>(order, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.GET, path = "/complete/{id}")
	public ResponseEntity<?> complete(@PathVariable("id") long orderId) {
		Optional<Order> orderOptional = orderRepository.findById(orderId);

		if (!orderOptional.isPresent()) {
			return new ResponseEntity<>("해당 ID에 해당하는 주문 없음", HttpStatus.BAD_REQUEST);
		}

		Order order = orderOptional.get();

		if (!order.getStatus().equals(Order.CONFIRMED)) {
			return new ResponseEntity<>("주문 완료가 불가능한 상태", HttpStatus.BAD_REQUEST);
		}

		order.setStatus(Order.COMPLETED);
		orderRepository.save(order);

		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/tookout/{id}")
	public ResponseEntity<?> tookOut(@PathVariable("id") long orderId) {
		Optional<Order> orderOptional = orderRepository.findById(orderId);

		if (!orderOptional.isPresent()) {
			return new ResponseEntity<>("해당 ID에 해당하는 주문 없음", HttpStatus.BAD_REQUEST);
		}

		Order order = orderOptional.get();

		if (!order.getStatus().equals(Order.COMPLETED)) {
			return new ResponseEntity<>("수취가 불가능한 상태", HttpStatus.BAD_REQUEST);
		}

		order.setStatus(Order.TOOKOUT);
		orderRepository.save(order);

		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/purchase")
	public String purchase(@RequestBody Order order) {
		return order.toString();
	}

}