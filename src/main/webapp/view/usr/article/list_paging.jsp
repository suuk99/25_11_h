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
		<div class="pagination">
    <c:if test="${paging.startPage > 1}">
        <a href="/usr/article/list?boardId=${boardId}&page=${paging.startPage - 1}">&laquo; 이전</a>
    </c:if>

    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i">
        <c:choose>
            <c:when test="${i == paging.page}">
                <span>${i}</span>
            </c:when>
            <c:otherwise>
                <a href="/usr/article/list?boardId=${boardId}&page=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${paging.endPage < paging.totalPage}">
        <a href="/usr/article/list?boardId=${boardId}&page=${paging.endPage + 1}">다음 &raquo;</a>
    </c:if>
</div>
		
		<div class="btn">
			<button onclick="history.back();">뒤로가기</button>
			<c:if test="${sessionScope.loginMemberId != null }">
				<a href="/usr/article/write">글쓰기</a>
			</c:if>
		</div>
	</nav>
	
<%@ include file="/view/usr/common/footer.jsp" %>