package cn.hncu.domain;

public class Book {
	private Integer id; //主键自动增长
	private String name; 
	private Double price;
	//多对一,存储一方的值对象
	private Student student;
	
	public Book() {
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", student=" + student + "]";
	} 
	
	
}
