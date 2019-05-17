<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"  %>
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
	<h4>Recent Spttles</h4>
	<div class="spittleView">
	<div class = "spittleId">
				<c:out value="${spittle.id}"/>
	</div>
	<div class = "spittleMessage">
		<c:out value="${spittle.message}"/>
	</div>
	<div>
		<span class="spittleTime">
			<c:out value="${spittle.time}"/>
		</span>
		<span class="spittlLocation">
			(<c:out value="${spittle.latitude}"/>
			<c:out value="${spittle.longitude}"/>)
		</span>
	</div>
	</div>
</body>
</html>