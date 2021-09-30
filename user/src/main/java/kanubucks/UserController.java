package kanubucks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(method=RequestMethod.GET, path="/login/{id}")
	public String login(@PathVariable("id") Long userId) {

		Optional<User> userOptional = userRepository.findById(userId);

		if (!userOptional.isPresent()) {
			return "해당 ID에 해당하는 User 없음";
		}

		User user = userOptional.get();

		if (user.getLoginStatus().equals(User.LOGIN_STATUS)) {
			return "해당 ID는 이미 로그인 상태";
		}

		user.setLoginStatus(User.LOGIN_STATUS);
		userRepository.save(user);

		return "성공";
	}

	@RequestMapping(method=RequestMethod.GET, path="/logout/{id}")
	public String logout(@PathVariable("id") Long userId) {

		Optional<User> userOptional = userRepository.findById(userId);

		if (!userOptional.isPresent()) {
			return "해당 ID에 해당하는 User 없음";
		}

		User user = userOptional.get();

		if (user.getLoginStatus().equals(User.LOGOUT_STATUS)) {
			return "해당 ID는 이미 로그아웃 상태";
		}

		user.setLoginStatus(User.LOGOUT_STATUS);
		userRepository.save(user);

		return "성공";
	}
}