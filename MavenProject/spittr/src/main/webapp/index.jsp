<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv = "Content-Type" content="text/html;charset=UTF-8"/>
	<!-- <meta http-equiv = "refresh" content="0;url=home"/> -->
<title>Spring 4 MVC - HelloWorld Index Page</title>
</head>
<body>
<center>
	<!-- <div id='header' th:include='page :: header'></div> -->
		<h2>Hello World!</h2>
		<h3>
			<a href="hello">点击跳转</a>
		</h3>
<!-- 		<h2><a th:href="@{/logout}">Logout</a></h2>
	<div id="footer" th:include='page :: copy'></div> -->
</center>
</body>
</html>
