package cn.hncu.stud.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.hncu.domain.Student;

public class StudDaoImpl extends SqlSessionDaoSupport implements StudDAO {
	

	@Override
	public String save(Student student) {
		SqlSession sqlSession = getSqlSession();
		sqlSession.insert("cn.hncu.domain.Student.insert",student);
		return null;
	}

	@Override
	public List<Student> queryAllStudent() {
		SqlSession sqlSession = getSqlSession();
		List<Student> students = sqlSession.selectList("cn.hncu.domain.Student.all");
		return students;
	}

}
