package kanubucks;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/status")
public class StatusController {

	@Autowired
	StatusTableRepository statusTableRepository;

	@RequestMapping(method=RequestMethod.GET, path="/status/employee/", produces="application/json; charset=UTF8")
	public String getViewForEmployee() {

		List<String> employeeStatus = new ArrayList<>();
		employeeStatus.add(StatusTable.PURCHASE_SUCCESS);
		employeeStatus.add(StatusTable.ORDER_APPROVED);
		employeeStatus.add(StatusTable.ORDER_COMPLETED);

		List<StatusTable> viewEmployee = statusTableRepository.findByStatusOrderByTimeDesc(employeeStatus);

		Gson gson = new Gson();
		String rtnString = gson.toJson(viewEmployee);

		return rtnString;
	}

	@RequestMapping(method=RequestMethod.GET, path="/status/user/", produces="application/json; charset=UTF8")
	public String getViewForUser() {

		List<StatusTable> viewUser = statusTableRepository.findAll();

		Gson gson = new Gson();
		String rtnString = gson.toJson(viewUser);

		return rtnString;
	}
}