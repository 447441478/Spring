package cn.hncu.user;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hncu.domain.User;
import cn.hncu.user.service.IUserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	//使用该注解时，会在Spring容器中找到name为userService的bean,然后进行注入。
	@Resource(name="userService")
	private IUserService userService;
	
	@RequestMapping(value="/save")
	public String save(User user) {
		userService.save(user);
		return "res";
	}
}
