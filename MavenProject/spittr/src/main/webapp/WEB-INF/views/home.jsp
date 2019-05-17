<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"  %>
<%@ page session="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spittr</title>
<link rel="stylesheet"
	  type="text/css"
	  href="<c:url value="/resources/style.css" /> "> 
</head>
<body>
	<h1>Welcome to Spittr</h1>
	<a href="<c:url value="/spittles" />" >spittles</a>
	<a href="<c:url value="/spitter/register" />" >Regisiter</a>
</body>
</html>