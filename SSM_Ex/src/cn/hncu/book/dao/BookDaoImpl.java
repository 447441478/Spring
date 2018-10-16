package cn.hncu.book.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.hncu.domain.Book;

public class BookDaoImpl extends SqlSessionDaoSupport implements BookDAO{
	
	
	@Override
	public String batchSave(List<Book> books) {
		SqlSession sqlSession = getSqlSession();
		System.out.println(sqlSession.hashCode());
		for (Book book : books) {
			sqlSession.insert("cn.hncu.domain.Book.insert", book);
		}
		return "ok";
	}

}
