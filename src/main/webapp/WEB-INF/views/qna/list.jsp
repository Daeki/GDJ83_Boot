<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>QnA List</h1>
	
	<table>
		<thead>
			<tr>
				<th>Num</th>
				<th>Title</th>
				<th>Writer</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="vo">
				<tr>
				<td>${vo.boardNum}</td>
				<td><a href="./detail?boardNum=${vo.boardNum}">${vo.boardTitle}</a></td>
				<td>${vo.boardWriter}</td>
				<td>${vo.createDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="./add">ADD</a>
</body>
</html>