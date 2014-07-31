<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Answerlist</title>
</head>
<body>
	<hr>
	<h4>AnswerList</h4>

	<div>
		<table border="1">
			<tr>
				<td>answerId</td>
				<td>answeQuestionId</td>
				<td>answerReplierId</td>
				<td>answerContent</td>
				<td>answerFavoriteNum</td>
				<td>answerDate</td>
			</tr>
			<c:forEach var="answer" items="${answerList}" varStatus="status">
				<tr>
					<td>${answer.id}</td>
					<td>${answer.questionId}</td>
					<td>${answer.replierId}</td>
					<td>${answer.content}</td>
					<td>${answer.favoriteNum}</td>
					<td>${answer.date}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>