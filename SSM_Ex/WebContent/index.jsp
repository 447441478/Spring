<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>SSM框架搭建</h1>
		<a href="<c:url value='/hncu/stud/all'/>">查询所有学生</a>
		<hr />
		<form action="<c:url value='/hncu/stud/add'/>" method="post">
			学生姓名：<input name="name" /><br/>
			图书名称1：<input name="books[0].name" /><br/>
			图书价格1：<input name="books[0].price" /><br/>
			图书名称2：<input name="books[1].name" /><br/>
			图书价格2：<input name="books[1].price" /><br/>
			<input type="submit" value="提交" />
		</form>
	</body>
</html>