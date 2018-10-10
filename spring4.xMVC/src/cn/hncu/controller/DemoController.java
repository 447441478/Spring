package cn.hncu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.util.Streams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import cn.hncu.domain.User;

/* 实现ServletContextAware接口就可在 ServletContext初始化
 * 通过监听者模式调用ServletContextAware接口的setServletContext()方法
 * 给 ctx 赋值，这样就可以在POJO中获取到ServletContext容器了。
 */
@Controller
public class DemoController implements ServletContextAware{ 
	private ServletContext ctx; //servletContext容器
	private String basePath; //项目的本地路径
	public ServletContext getCtx() {
		return ctx;
	}
	public void setCtx(ServletContext ctx) {
		this.ctx = ctx;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	@Override //该方法是ServletContextAware接口的方法！！！
	public void setServletContext(ServletContext servletContext) {
		ctx = servletContext;
		basePath = servletContext.getRealPath("/");
	}
	
	
	// http://127.0.0.1:8080/spring4.xMVC/hncu/demo1?name=张三
	// http://127.0.0.1:8080/spring4.xMVC/hncu/demo1?name=张三&age=22
	@RequestMapping(value="/demo1",method={RequestMethod.GET})
	public String demo1(String name,Integer age) { //使用包装类，否则会出现null异常
		System.out.println("demo1..."+name+","+age);
		return "res";
	}
	
	/* 使用@RequestParam注解被注解的参数默认是必填的
	 *     http://127.0.0.1:8080/spring4.xMVC/hncu/demo2    错误代码400
	 * 可以设置required为false
	 * 还可以设置前端的name为'nm'才封装给被注解的参数
	 *     http://127.0.0.1:8080/spring4.xMVC/hncu/demo2?nm=张三
	 * 
	 */
	@RequestMapping(value="/demo2")
	public String demo2(@RequestParam(required=false,name="nm") String name,Integer age) { //使用包装类，否则会出现null异常
		System.out.println("demo2..."+name+","+age);
		return "res";
	}
	
	/* 使用@PathVariable注解可以解析url路径
	 *     http://127.0.0.1:8080/spring4.xMVC/hncu/demo3/123/abc/stud
	 *     ---> path=123,path2=stud
	 */
	@RequestMapping(value="/demo3/{p}/abc/{pp}")
	public String demo3(@PathVariable(value="p") String path,@PathVariable(value="pp") String path2) { //使用包装类，否则会出现null异常
		System.out.println("demo3..."+path +","+path2);
		return "res";
	}
	
	/* http://127.0.0.1:8080/spring4.xMVC/hncu/demo4?name=张三&age=22
	 * 使用Model对象调用addAttribute()相当于 request.setAttribute()
	 * 还可在方法参数给的 req、session、resp、out 等
	 * 注意  OutputStream 和  Model 不能共存
	 */
	@RequestMapping(value="/demo4")
	public String demo4(User user, Model model,
			HttpServletRequest req,
			HttpSession session,
			HttpServletResponse resp) { //resp的输出流
		System.out.println("demo4...");
		model.addAttribute("user", user);
		return "res";
	}
	
	/* 使用@RequestHeader注解可以获得请求头信息
	 * 使用@CookieValue注解可以获得cookie的信息
	 */
	@RequestMapping(value="/demo5")
	public String demo5(@RequestHeader(name="accept")String accept,
			@CookieValue(name="JSESSIONID")String sessionid
			){
		System.out.println(accept);
		System.out.println(sessionid);
		return "res";
	}
	/* http://127.0.0.1:8080/spring4.xMVC/hncu/demo6?name=张三&age=22&addr.id=a001&addr.name=hncu
	 * 通过使用 addr.id=a001&addr.name=hncu 可以级联到User中的 Addr属性
	 */
	@RequestMapping(value="/demo6")
	public String demo6(User user,Model model){
		System.out.println(user);
		model.addAttribute("user",user);
		return "res";
	}
	
	////////////文件下载//////////////
	@RequestMapping(value="/demo7")
	public void demo7( OutputStream out, HttpServletResponse resp ) {
		
		//设置文件下载响应头
		resp.setHeader("Content-Disposition", "attachment;filename=a.txt");
		resp.setContentType("application/force-download;charact=utf-8");
		
		File file = new File(basePath+"/file/a.txt");
		if( file != null && file.isFile()) {
			try {
				InputStream in = new FileInputStream(file);
				//使用appache的工具包commons-io进行流拷贝
				Streams.copy(in, out, true);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	////////文件上传//////
	/* 需要 apache的 commons-fileupload-X.X.X.jar和commons-io-X.X.jar
	 * 同时需要 beans.xml中配置 <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver" />
	 */
	//上传单个文件
	@RequestMapping(value="/demo8")
	public String demo8(@RequestParam(name="file") MultipartFile file ) { //必须使用MultipartFile接口进行获取上传的文件
		try {
			InputStream in = file.getInputStream();
			//获取上传文件的文件名   解决中文乱码
			String fileName = new String(file.getOriginalFilename().getBytes("iso8859-1"),"utf-8");
			System.out.println( fileName );
			OutputStream out = new FileOutputStream(basePath+"/file/"+fileName);
			Streams.copy(in, out, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "res";
	}
	
	//上传多个文件，使用list再封装一层
	@RequestMapping(value="/demo9")
	public String demo9(@RequestParam(name="files") ArrayList<MultipartFile> files ) {
		if( files != null ) {
			for (MultipartFile file : files) {
				//当文件不存在时跳过，预防空指针
				if( file == null || file.isEmpty() ) {
					continue;
				}
				try {
					InputStream in = file.getInputStream();
					//获取上传文件的文件名   解决中文乱码
					String fileName = new String(file.getOriginalFilename().getBytes("iso8859-1"),"utf-8");
					System.out.println( fileName );
					OutputStream out = new FileOutputStream(basePath+"/file/"+fileName);
					Streams.copy(in, out, true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return "res";
	}
	
}
