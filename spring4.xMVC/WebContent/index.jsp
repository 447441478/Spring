<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Hello SpringMVC</title>
	</head>
	<body>
		<h1>Hello SpringMVC</h1>
		<a href="<c:url value='/hncu/stud/one'/>">注解1111</a>
		<hr/>
		<p>单文件上传</p>
		<form enctype="multipart/form-data" method="post" action="<c:url value='/hncu/demo8'/>">
			<input type="file" name="file" /><br/>
			<input type="submit" value="上传" />
		</form>
		<hr/>
		<p>多文件上传</p>
		<form enctype="multipart/form-data" method="post" action="<c:url value='/hncu/demo9'/>">
			<input type="file" name="files" /><br/>
			<input type="file" name="files" /><br/>
			<input type="file" name="files" /><br/>
			<input type="submit" value="上传" />
		</form>
		
		<hr/>
		<p>下面演示Controller+service+dao</p>
		<form method="post" action="<c:url value='/hncu/user/save'/>">
			Name:<input type="text" name="name" /><br/>
			Age:<input type="text" name="age" /><br/>
			AddrID:<input type="text" name="addr.id" /><br/>
			AddrName:<input type="text" name="addr.name" /><br/>
			<input type="submit" value="提交" />
		</form>
	</body>
</html>