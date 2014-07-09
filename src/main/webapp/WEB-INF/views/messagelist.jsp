<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Followlist</title>
</head>
<body>
	<hr>
	<h4>FollowList</h4>

	<div>
		<table border="1">
			<tr>
				<td>messageId</td>
				<td>messageReceiverId</td>
				<td>messageIsChecked</td>
				<td>messageType</td>
				<td>messageSourceId</td>
			</tr>
			<c:forEach var="message" items="${messageList}" varStatus="status">
				<tr>
					<td>${message.id}</td>
					<td>${message.receiverId}</td>
					<td>${message.isChecked}</td>
					<td>${message.type}</td>
					<td>${message.sourceId}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>