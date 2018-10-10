package cn.hncu.user.service;


import org.springframework.stereotype.Service;

import cn.hncu.domain.User;
import cn.hncu.user.dao.UserDAO;

/*
采用该注解配合@Resource注解给Controller注入IUserService接口的实现类，
但是这样就有依赖不推荐这样，因为后期维护时需要修改该类的代码
*/
//@Service("userService") 
public class UserServiceImpl2 implements IUserService{
	private UserDAO userDao;
	public UserDAO getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	@Override
	public String save(User user) {
		System.out.println("22222222222");
		return userDao.save(user);
	}

}
