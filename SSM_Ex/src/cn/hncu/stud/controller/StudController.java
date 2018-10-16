package cn.hncu.stud.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.hncu.domain.Book;
import cn.hncu.domain.Student;
import cn.hncu.stud.service.IStudService;

@Controller
@RequestMapping("/stud")
public class StudController {
	@Resource(name="studService")
	private IStudService studService;
	
	
	@RequestMapping("/all")
	public String showAllStudent(Model model) {
		System.out.println("进来了");
		List<Student> students = studService.queryAllStudent();
		model.addAttribute("students", students);
		return "stud/all";
	}
	
	@RequestMapping("/add")
	public String addStudAndBook(Student student,Model model) {
		System.out.println( student );
		System.out.println( student.getBooks() );
		List<Book> books = student.getBooks();
		for (Book book : books) {
			book.setStudent(student);
		}
		String msg = studService.save(student);
		model.addAttribute("msg", msg);
		return "stud/all";
	}
}
