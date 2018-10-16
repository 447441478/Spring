package cn.hncu.stud.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.hncu.book.dao.BookDAO;
import cn.hncu.domain.Student;
import cn.hncu.stud.dao.StudDAO;

public class StudServiceImpl implements IStudService {
	//注入dao
	private StudDAO studDao;
	private BookDAO bookDao;
	public void setStudDao(StudDAO studDao) {
		this.studDao = studDao;
	}
	public void setBookDao(BookDAO bookDao) {
		this.bookDao = bookDao;
	}
	
	@Transactional
	@Override
	public String save(Student student) {
		
		String msg1 = studDao.save(student);
		
		String msg2 = bookDao.batchSave(student.getBooks());
		
		return msg1+msg2;
	}
	@Override
	public List<Student> queryAllStudent() {
		return studDao.queryAllStudent();
	}

}
