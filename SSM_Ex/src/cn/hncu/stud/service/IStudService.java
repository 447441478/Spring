package cn.hncu.stud.service;

import java.util.List;

import cn.hncu.domain.Student;


public interface IStudService {
	
	String save(Student student);
	
	List<Student> queryAllStudent();
}
