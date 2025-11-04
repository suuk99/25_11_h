<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
</head>
<body>
	<div>리스트</div>
	
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
		</tr>
		<c:forEach items="${articles }" var="article">
			<tr>
				<td>${article.getId() }</td>
				<td>${article.getTitle() }</td>
				<td>${article.getRegDate() }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>