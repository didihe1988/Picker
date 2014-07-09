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
				<td>followId</td>
				<td>followFollowerId</td>
				<td>followSourceType</td>
				<td>followSourceId</td>
			</tr>
			<c:forEach var="follow" items="${followList}" varStatus="status">
				<tr>
					<td>${follow.id}</td>
					<td>${follow.followerd}</td>
					<td>${follow.sourceType}</td>
					<td>${follow.sourceId}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>