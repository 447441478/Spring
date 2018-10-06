package cn.hncu.ioc.v1;

public class User {
	private String name;
	public User() {
		System.out.println("调用了空参构造...");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
}
