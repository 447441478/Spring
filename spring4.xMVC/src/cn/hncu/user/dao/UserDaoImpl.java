package cn.hncu.user.dao;

import org.springframework.stereotype.Repository;

import cn.hncu.domain.User;

/*
采用该注解配合@Resource注解给UserServiceImpl注入UserDAO接口的实现类，
但是这样就会有依赖不推荐这样，因为后期维护时需要修改该类的代码
*/
//@Repository("userDao")
public class UserDaoImpl implements UserDAO {

	@Override
	public String save(User user) {
		System.out.println("到dao一游："+user);
		return null;
	}

}
