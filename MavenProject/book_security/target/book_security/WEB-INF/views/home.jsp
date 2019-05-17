<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"  %>
<%@ page session="false" pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>

<style>
	.hidden{
		position:fixed;
		margin-right:70%;
		height:100;
		left:200px;
		top:200px;
		background-color:b0c4de;
		display:none
		
	}
	table, tr, td{
		border-collapse:collapse;
		border:1px solid black;
		
	}
	td{
		text-align:center;
	}
</style>
	 
</head>
<body>
	<input type="button" value="ADD" onclick="showAddDiv()"/>
	<input type="button" value="DELETE" onclick="deleteButton()"/>
	<input type="button" value="MODIFY" onclick="showModifyDiv()"/>
	<input type="button" value="QUERY" onclick="showQueryDiv()"/>
	<div id="addDiv" class="hidden">
		<center>ADD</center>
		<form method="post" onsubmit="return addSubmit()"
		action="/book/home/add/">
			
			Title:<input type="text" name="title"/><br>
			Author:<input type="text" name="author"/><br>
			page:<input type="text" name="page"/><br>
			price:<input type="text" name="price"/><br>
			<input type="submit" value="  OK  " />
		</form>
	</div>
	<div id="modifyDiv" class="hidden">
		<center>MODIFY</center>
		<form method="post" onsubmit="return submit()"
		action="/book/home/modify/" target="nm_iframe">
			<input type="hidden" name="id" id="modifyId"/><br>
			Title:<input type="text" name="title" id="modifyTitle"/><br>
			Author:<input type="text" name="author" id="modifyAuthor"/><br>
			page:<input type="text" name="page" id="modifyPage"/><br>
			price:<input type="text" name="price" id="modifyPrice"/><br>
			<input type="submit" value="  Modify  " />
		</form>
	
	</div>
	<div id="queryDiv" class="hidden">
		<center>QUERY</center>
		Find By: <select id="select" width="80">
					<option value="id">Id</option>
					<option value="title">Title</option>
					<option value="author">Author</option>
				 </select><br>
		Key word: <input type="text" id="keyword"/><br>
		<input type="button" value="query" id="querybutton" onclick="queryButton()"/>&nbsp&nbsp&nbsp&nbsp
		<input type="button" value="showAll" 
		 onclick="window.location.href='/book/home'"/>
	
		
	</div>
	<table border="1" width="600px" align="center" id="booklist">
		<caption>Book List</caption>
		<tr >
			<th>checked</th>
			<th>id</th>
			<th>title</th>
			<th>author</th>
			<th>page</th>
			<th>price</th>
		</tr>
		<c:forEach items="${books }"  var="book">
	
		<tr onmouseenter="moseMoveTo(this)" onclick="clickTr(this)">
			<td><input type="radio" name="bookItems"  onclick="changeRadio(this)"
				value =	"<c:out value="${book.id}"/>"/>
			</td>
			<td><c:out value="${book.id}"/></td>
			<td><c:out value="${book.title}"/></td>
			<td><c:out value="${book.author}"/></td>
			<td><c:out value="${book.page}"/></td>
			<td><c:out value="${book.price}"/></td>
		</tr> 
		</c:forEach> 
	</table>
</body>
<script>
	function clickTr(obj){
		
	}
	function changeRadio(radio){
		changeV(radio.value)
	}
	function showDiv(divId){
		var div = document.getElementById(divId);
		if(div.style.display=="block"){
			div.style.display="none";
		}else{
			closeDivExcept(divId);
			div.style.display="block";
		}
	}
	function closeDivExcept(divId){
		var data=["modifyDiv","queryDiv","addDiv"];
		for(var i=0;i<data.length;i++){
			
			var div = document.getElementById(data[i]);
			if(divId!=data[i]&&div.style.display=="block"){
				div.style.display="none";
			}
		}
		
	}
	function showModifyDiv(){
		var item = getSelectedRadio();
		if(item==null){
			alert("Please choose an item!")
			return;
		}
		var modify = document.getElementById("modifyDiv");
		if(modify.style.display=="block"){
			if(item.value==document.getElementById("modifyId").value){
				modify.style.display="none";
				return;
			}
		}else{
			closeDivExcept()
			modify.style.display="block";
		}
		changeV(item.value);
		
	}
	function changeV(value){
		var table = document.getElementById("booklist")
		
		var data=["Id","Title","Author","Page","Price"];
		
		for(var i=1,rows=table.rows.length;i<rows;i++){
			if(table.rows[i].cells[1].innerHTML== value){
				start = 1;
				for(var j=1;j<table.rows[i].cells.length;j++){
					
					var temp=document.getElementById("modify"+data[j-1]);
					temp.value=table.rows[i].cells[j].innerHTML;		
				}
				break;
			}
		}
	}
	function showAddDiv(){
		showDiv("addDiv");
	}
	function showQueryDiv(){
		showDiv("queryDiv");
	}
	function queryButton(){
		var add = document.getElementById("querybutton");
		var findBy = document.getElementById("select");
		var keyword = document.getElementById("keyword");
		if(keyword==null||keyword.value.length==0){
			alert("keyword can't be null!");
		}else{
			if(findBy.value=="id"){
				if(isNaN(keyword.value)){
					alert("The type of the keyword that you input should be number");
					return;
				}
			}
			window.location.href="/book/home/"+findBy.value+"/"+keyword.value;
		}
		
	}
	
	function deleteButton(){
		var item = getSelectedRadio();
 		if(item == null) {
 			alert("Please choose an item!");
		}
		else{
			window.location.href="/book/home/delete/"+item.value;
		}  
		
	}
	function addSubmit(){
		var username=document.getElementById("username").value;
		var password=document.getElementById("password").value;
		if(username.length==0||password.length==0||username.length==null||password.length==null){
			alert("username and password can't be null")
			return false;
		}else{
			return true;
		}
		
	}
	function getSelectedRadio(){
		var checkItem = document.getElementsByName("bookItems");
		for(var i=0;i<checkItem.length;i++){
			var item = checkItem[i];
			if(item.checked){
				return item
			}
		}
		return null;
	}
	function moseMoveTo(obj){
		var table = document.getElementById("booklist");
		var t=0;
		for(var i=1;i<table.rows.length;i++){
			if(table.rows[i]==obj)
				t=1;
			else
				table.rows[i].style.backgroundColor="#ffffff";
		}
		obj.style.backgroundColor="#797979";
	}
</script>
</html>