
package kanubucks.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="menu", url="${api.menu.url}")
public interface MenuService {

    @RequestMapping(method=RequestMethod.GET, path="/menus/{id}")
    public Menu getMenu(@PathVariable("id") Long menuId);

}