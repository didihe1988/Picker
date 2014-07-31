<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>QuestionList</title>
</head>
<body>
	<hr>
	<h4>QuestionList</h4>

	<div>
		<table border="1">
			<tr>
				<td>id</td>
				<td>bookId</td>
				<td>askerId</td>
				<td>content</td>
				<td>favoriteNum</td>
				<td>date</td>
			</tr>
			<c:forEach var="question" items="${questionList}" varStatus="status">
				<tr>
					<td>${question.id}</td>
					<td>${question.bookId}</td>
					<td>${question.askerId}</td>
					<td>${question.content}</td>
					<td>${question.favoriteNum}</td>
					<td>${question.date}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>