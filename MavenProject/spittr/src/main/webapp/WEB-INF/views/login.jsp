<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload='document.f.username.focus();'>
	<div id='header' th:include='page :: header'></div>
	<div id="content">
		<form name='f' th:action='@{/login}' method="post">
			<input type="hidden" name="${_csrf.parameterName }" 
				value="${csrf.token }"/>
			<table>
				<tr><td>User:</td><td>
					<input type="text" name="username" value=""/></td></tr>
				<tr><td>Password:</td><td>
					<input type="password" name="password" value=""/></td></tr>
				<tr><td colspan='2'>
					<input type="submit" name="submit" value="Login"/></td></tr>
				<input id="remember_me" name="remember-me" type="checkbox"/>
				<label for="remember_me" class="inline">Remember me</label>
			</table>
		</form>
	</div>
	<div id="footer" th:include='page :: copy'></div>
</body>
</html>