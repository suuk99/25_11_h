<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="수정"/>

<%@ include file="/view/usr/common/header.jsp" %>
	<nav class="list">
		<div class="top_name">수정</div>
		
		<form action="/usr/article/doModify" method="post" onsubmit="return submitFromChk(this)">
			<input name="id" type="hidden" value="${article.getId() }"/>
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
					<th>제목</th>
					<td><input class="input input-neutral" name="title" type="text" value="${article.getTitle() }" /></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea class="input input-neutral" name="content">${article.getContent() }</textarea></td>
				</tr>
				<tr>
					<td colspan="2"><button>수정</button></td>
				</tr>
			</table>
		</form>
		
		<div class="btn">
			<button onclick="history.back();">뒤로가기</button>
			<a href="/usr/article/delete?id=${article.getId() }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
		</div>
	</nav>
<%@ include file="/view/usr/common/footer.jsp" %>