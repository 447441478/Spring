package cn.hncu.ioc.v2.dao;

public class LoginDaoOracle implements LoginDAO {

	@Override
	public String login(String username, String password) {
		System.out.println("进行Oracle数据库操作..."+username+","+password);
		return "success";
	}
	
}
