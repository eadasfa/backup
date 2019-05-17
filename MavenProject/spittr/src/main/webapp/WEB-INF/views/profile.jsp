<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"  %>
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spitter</title>
<link rel="stylesheet"
	  type="text/css"
	  href="<c:url value="/resources/style.css" /> ">
</head>
<body>
	<h4>Your Profile</h4>
	<c:out value="${spitter.userName }"/><br>
	<c:out value="${spitter.firstName }"/>
		<c:out value="${spitter.lastName }"/><br>
	
</body>
</html>