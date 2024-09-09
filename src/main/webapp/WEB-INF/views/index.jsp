<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Index Page</h1>
	<img alt="" src="/images/dog2.jpg">
	
	<spring:message code="hello"></spring:message>
	<spring:message code="hello2" text="기본값"></spring:message>
	
	<c:if test="${empty member}">
		<h1>Login 하기 전</h1>
	</c:if>
	
	<c:if test="${not empty member}">
		<h1>Login 성공</h1>
		<spring:message code="member.login.message" arguments="${member.username},${member.email}" argumentSeparator=","></spring:message>
		<c:forEach items="${member.vos}" var="r">
			<h3>${r.roleName}</h3>
		</c:forEach>
	</c:if>	
</body>
</html>