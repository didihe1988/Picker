<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.didihe1988.picker.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booklist</title>
</head>
<body>
	<!-- <a href="/book/list_android.do">test list_anroid.do</a>-->
	<!--  我在想session应该搁user还是userid -->
	<div>
		<%
			User user = (User) request.getSession().getAttribute("picker_user");
		%>
		<h4>User(session):</h4>
		id-<%=user.getId()%><br> username -
		<%=user.getUsername()%><br> password -
		<%=user.getPassword()%><br> lastVisit -
		<%=user.getLastVisit()%><br>
	</div>
	<hr>
	<h4>BookList</h4>
	<!-- 
	<c:forEach var="book" items="${bookList}" varStatus="status">
		 bookid: ${book.id}   bookname: ${book.bookName}   isbn: ${book.isbn}   press: ${book.press} followNum: ${book.followNum}  commentNum: ${book.commentNum}          
		<br>
	</c:forEach>
	 -->
	<div>
		<table border="1">
			<tr>
				<td>bookId</td>
				<td>bookName</td>
				<td>isbn</td>
				<td>press</td>
				<td>followNum</td>
				<td>commentNum</td>
			</tr>
			<c:forEach var="book" items="${bookList}" varStatus="status">
				<tr>
					<td>${book.id}</td>
					<td>${book.bookName}</td>
					<td>${book.isbn}</td>
					<td>${book.press}</td>
					<td>${book.followNum}</td>
					<td>${book.commentNum}</td>
					<!-- <a
					
						href=<c:url value="comment/list.do">
						 <c:param name="bookId" value="${book.id}"/> 
						</c:url>> 
					</a> -->
					<a
						href=<c:url value="/book/detail.do">
						 <c:param name="bookId" value="${book.id}"/> 
						</c:url>><td>detail</td></a>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<!--  
	<a href="/picker/book/detail.do?bookId=4">test_hyperlink01</a>
	<a href="/picker/register.html">test_hyperlink02</a> -->

</body>
</html>