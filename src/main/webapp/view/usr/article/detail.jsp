<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="상세"/>

<%@ include file="/view/usr/common/header.jsp" %>
	<nav class="list">
		<div class="top_name">상세보기</div>
		
		<table border="1">
			<tr>
				<th>번호</th>
				<td>${article.getId() }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${article.getRegDate() }</td>
			</tr>
			<tr>
				<th>수정일</th>
				<td>${article.getUpdateDate() }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${article.getWriterName() }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${article.getTitle() }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td class="content">${article.getContent() }</td>
			</tr>
		</table>
		
		<div class="btn">
			<button onclick="history.back();">뒤로가기</button>
			<c:if test="${sessionScope.loginMemberName == article.writerName}">
				<a href="/usr/article/modify?id=${article.getId() }">수정</a>
				<a href="/usr/article/delete?id=${article.getId() }&boardId=${article.getBoardId()}" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
			</c:if>
		</div>
	</nav>
<%@ include file="/view/usr/common/footer.jsp" %>