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
				<td>followProducer</td>
				<td>content</td>
				<td>favoriteNum</td>
				<td>favorite</td>
				<td>unfavorite</td>
			</tr>
			<c:forEach var="comment" items="${commentList}" varStatus="status">
				<tr>
					<td>${comment.id}</td>
					<td>${comment.bookId}</td>
					<td>${comment.receiverId}</td>
					<td>${comment.producerId}</td>
					<a
						href=<c:url value="/follow/add.do">
						 <c:param name="sourceType" value="1"/> 
						 <c:param name="sourceId" value="${comment.producerId}"/> 
						</c:url>><td>followProducer</td></a>
					<td>${comment.content}</td>
					<td>${comment.favoriteNum}</td>
					<a
						href=<c:url value="/comment/increment_favorite.do">
						 <c:param name="commentId" value="${comment.id}"/> 
						 <c:param name="bookId" value="${comment.bookId}"/> 
						</c:url>><td>favorite</td></a>
					<a
						href=<c:url value="/comment/decrement_favorite.do">
						 <c:param name="commentId" value="${comment.id}"/> 
						 <c:param name="bookId" value="${comment.bookId}"/> 
						</c:url>><td>unfavorite</td></a>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>