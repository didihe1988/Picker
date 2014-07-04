<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bookdetail</title>
</head>
<body>

	<hr>
	<h4>Bookdetail</h4>

	bookid: ${book.id} bookname: ${book.bookName} isbn: ${book.isbn} press:
	${book.press} followNum: ${book.followNum} commentNum:
	${book.commentNum}
	<br>
	<div>
		<h4>Comment</h4>
		<table border="1">
			<tr>
				<td>commentId</td>
				<td>bookId</td>
				<td>receiverId</td>
				<td>producerId</td>
				<td>content</td>
				<td>favoriteNum</td>
			</tr>
			<c:forEach var="comment" items="${commentList}" varStatus="status">
				<tr>
					<td>${comment.id}</td>
					<td>${comment.bookId}</td>
					<td>${comment.receiverId}</td>
					<td>${comment.producerId}</td>
					<td>${comment.content}</td>
					<td>${comment.favoriteNum}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>