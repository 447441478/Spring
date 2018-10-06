package cn.hncu.ioc.v2.service;

import cn.hncu.ioc.v2.dao.LoginDAO;

public class LoginServiceImpl implements ILoginService {

	//注入 dao
	private LoginDAO dao; 
	//采用 Spring 注入必须 写上相应的setter/getter方法！！！
	public LoginDAO getDao() {
		return dao;
	}
	public void setDao(LoginDAO dao) {
		this.dao = dao;
	}

	@Override
	public String login(String username, String password) {
		return dao.login(username, password);
	}

}
