<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="${boardName }"/>

<%@ include file="/view/usr/common/header.jsp" %>
	<nav class="list">
		<div class="top_name">${boardName}</div>
		
		<table border="1">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach items="${articles }" var="article">
				<tr>
					<td>${article.getId() }</td>
					<td><a href="/usr/article/detail?id=${article.getId() }">${article.getTitle() }</a></td>
					<td>${article.getWriterName() }</td>
					<td>${article.getRegDate() }</td>
				</tr>
			</c:forEach>
		</table>
		
		<div class="btn">
			<button onclick="history.back();">뒤로가기</button>
			<c:if test="${sessionScope.loginMemberId != null }">
				<a href="/usr/article/write">글쓰기</a>
			</c:if>
		</div>
	</nav>
	
<%@ include file="/view/usr/common/footer.jsp" %>