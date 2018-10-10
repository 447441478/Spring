package cn.hncu.domain;

public class Addr {
	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Addr() {
	}
	@Override
	public String toString() {
		return "Addr [id=" + id + ", name=" + name + "]";
	}
	
}
