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
	<h1>Index Page</h1>
	<img alt="" src="/images/dog2.jpg">
	
	<c:if test="${empty member}">
		<h1>Login 하기 전</h1>
	</c:if>
	
	<c:if test="${not empty member}">
		<h1>Login 성공</h1>
		<c:forEach items="${member.vos}" var="r">
			<h3>${r.roleName}</h3>
		</c:forEach>
	</c:if>	
</body>
</html>