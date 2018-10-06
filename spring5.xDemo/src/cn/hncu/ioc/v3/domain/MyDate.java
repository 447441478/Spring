package cn.hncu.ioc.v3.domain;

public class MyDate {
	private String date;

	public MyDate() {
	}
	
	@Override
	public String toString() {
		return "MyDate [date=" + date + "]";
	}


	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
