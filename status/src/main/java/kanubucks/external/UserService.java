
package kanubucks.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="user", url="${api.user.url}")
public interface UserService {

    @RequestMapping(method=RequestMethod.GET, path="/users/{id}")
    public User getUser(@PathVariable("id") Long userId);

}