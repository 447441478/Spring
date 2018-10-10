package cn.hncu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/stud") //访问地址:http://127.0.0.1:8080/spring4.xMVC/hncu/stud/one
public class StudController {
	
	@RequestMapping(value="/one")
	public String one() {
		System.out.println("hello");
		return "stud_one";
	}
}
