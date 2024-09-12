<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>My Page</h1>
		<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal" var="vo"/>
		<h3>${vo.username}</h3>
		<h3>${vo.name} </h3>
		<h3><sec:authentication property="principal.email"/></h3>
		<h3><sec:authentication property="name"/> </h3>
	</sec:authorize>
	<a href="./update">회원수정</a>

</body>
</html>