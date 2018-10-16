package cn.hncu.domain;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private Integer id;
	private String name;
	
	//一对多，存储多方的集合，并且new出来
	private List<Book> books=new ArrayList<Book>();
	
	public Student() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
}
