<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"  %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"  %>
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spittles</title>
<link rel="stylesheet"
	  type="text/css"
	  href="<c:url value="/resources/style.css" /> ">
</head>
<body>
	<h1>Register</h1>

	<sf:form method="POST" commandName="spitter"
		enctype="multipart/form-data">
		<input type="hidden" name="${_csrf.parameterName }" value="${csrf.token }"/>
		Username:<sf:input path="userName"/><br>
		First name:<sf:input path="firstName"/><br>
		Last name:<sf:input path="lastName"/><br>
		Password:<sf:input path="password"/><br>
		<label>Profile Picture</label>:
			<input type="file" name="profilePicture"
			accept="image/jpeg,image/png,image/gif"/><br>
		<input type="submit" value="Register"/>
		
	</sf:form>
</body>
</html>