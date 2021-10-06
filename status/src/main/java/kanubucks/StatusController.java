package kanubucks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/status")
public class StatusController {

	@Autowired
	StatusTableRepository statusTableRepository;

	@RequestMapping(method=RequestMethod.GET, path="/status/employee/")
	public String getViewForEmployee() {

		List<String> employeeStatus = new ArrayList<>();
		employeeStatus.add(StatusTable.PURCHASE_SUCCESS);
		employeeStatus.add(StatusTable.ORDER_APPROVED);
		employeeStatus.add(StatusTable.ORDER_COMPLETED);

		List<StatusTable> viewEmployee = statusTableRepository.findByStatusOrderByTimeDesc(employeeStatus);

		return viewEmployee.toString();
	}

	@RequestMapping(method=RequestMethod.GET, path="/status/user/")
	public String getViewForUser() {

		List<StatusTable> viewUser = statusTableRepository.findAll();

		return viewUser.toString();
	}
}