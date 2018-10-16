package cn.hncu.stud.dao;

import java.util.List;

import cn.hncu.domain.Student;

public interface StudDAO {
	String save(Student student);

	List<Student> queryAllStudent();
}
