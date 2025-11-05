<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="목록"/>

<%@ include file="/view/usr/common/header.jsp" %>

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
				<td><a href="/usr/article/detail?id=${article.getId() }">${article.getTitle() }</a></td>
				<td>${article.getRegDate() }</td>
			</tr>
		</c:forEach>
	</table>
	
	<div>
		<a href="/usr/article/write">글쓰기</a>
	</div>
	
<%@ include file="/view/usr/common/footer.jsp" %>