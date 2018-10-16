package cn.hncu.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hncu.domain.Book;
import cn.hncu.domain.Student;
import cn.hncu.stud.service.IStudService;

public class SSMTest {
	
	//测试数据库连接池
	@Test
	public void t1() throws SQLException {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("beans.xml");
		DataSource dataSource = appCtx.getBean("dataSource",DataSource.class);
		Connection con = dataSource.getConnection();
		Statement st = con.createStatement();
		ResultSet resultSet = st.executeQuery("show databases");
		while ( resultSet.next() ) {
			System.out.println( resultSet.getString(1) );
		}
		
		((AbstractApplicationContext) appCtx).close();
	}
	
	//测试使用c3p0的MyBatis
	@Test
	public void t2() {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("beans.xml");
		SqlSessionFactory sqlSessionFactory = appCtx.getBean(SqlSessionFactory.class);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Student> students = sqlSession.selectList("all");
		for (Student student : students) {
			System.out.println( student );
		}
		
		
		((AbstractApplicationContext) appCtx).close();
	}
	
	@Test
	public void t3() {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("beans.xml");
		SqlSessionFactory sqlSessionFactory = appCtx.getBean(SqlSessionFactory.class);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Book book = new Book();
		book.setName("123421");
		sqlSession.insert("cn.hncu.domain.Book.insert",book);
		Book book2 = new Book();
		book2.setName("asdasassssssssssadasdddddddd");
		sqlSession.insert("cn.hncu.domain.Book.insert",book2);
		((AbstractApplicationContext) appCtx).close();
	}
	
	//测试事务
	public static void main(String[] args) {
		
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("beans.xml");
		IStudService studService = appCtx.getBean( "studService", IStudService.class );
		System.err.println( studService );
		new MyThread(studService,1).start();
		new MyThread(studService,2).start();
		
		((AbstractApplicationContext) appCtx).close();
	}
}
class MyThread extends Thread{
	
	private IStudService studService;
	private Student student;
	public MyThread(final IStudService studService,int i) {
		this.studService = studService;
		if( i == 1 ) {
			student = new Student();
			student.setName("老干妈");
			
			Book book1 = new Book();
			book1.setName("近代史");
			book1.setPrice(28.88);
			student.getBooks().add(book1);
			book1.setStudent(student);
			
			Book book2 = new Book();
			book2.setName("高等数学111111111111111111111111111111111111");
			book2.setPrice(33.25);
			student.getBooks().add(book2);
			book2.setStudent(student);
			
		}else {
			student = new Student();
			student.setName("马画藤");
			
			Book book = new Book();
			book.setName("Game");
			book.setPrice(66.66);
			student.getBooks().add(book);
			book.setStudent(student);
		}
	}

	@Override
	public void run() {
		try {
			studService.save(student);
			System.out.println("提交了...");
		} catch (Exception e) {
			System.err.println(e);
			System.out.println("回滚了...");
		}
	}
}
