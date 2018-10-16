package cn.hncu.book.dao;

import java.util.List;

import cn.hncu.domain.Book;

public interface BookDAO {
	
	String batchSave(List<Book> books);
}
