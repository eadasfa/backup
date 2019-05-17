<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Register</title>
	<meta http-equiv = "Content-Type" content="text/html;charset=UTF-8"/>
	<script>
	function clickLogin(){
		var username=document.getElementById("username").value;
		var password=document.getElementById("password").value;
		if(username.length==0||password.length==0||username.length==null||password.length==null){
			alert("username and password can't be null")
			return false;
		}else{
			return true;
		}	
	}
</script>
</head>
<body>
	<center>
		<h4>Register</h4><br><br>
		<form method="post" onsubmit="return clickLogin()">
			Username:<input type="text" name="username" id="username"/><br>
			Password:<input type="password" name="password" id="password"/><br>
			Fullname:<input type="text" name="fullname" id="fullname"/><br>
			man:&nbsp&nbsp<input type="radio" value="man" name="sex" checked/>&nbsp&nbsp&nbsp&nbsp
				woman<input type="radio" value="woman" name="sex"/>&nbsp&nbsp&nbsp&nbsp&nbsp<br>
			<input type="submit" value="注册" "/>&nbsp &nbsp
		
		</form>
	</center>
</body>

</html>