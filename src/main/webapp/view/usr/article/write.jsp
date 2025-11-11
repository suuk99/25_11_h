<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="작성"/>

<%@ include file="/view/usr/common/header.jsp" %>
	
	<nav class="list">
		<div class="top_name">글쓰기</div>
		
		<form action="/usr/article/doWrite" method="post" onsubmit="return submitFormChk(this)_mb">
			<table border="1">
				<tr>
					<th>제목</th>
					<td><input class="input input-neutral" name="title" type="text" placeholder="제목을 입력하세요."/></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea class="input input-neutral" name="content" placeholder="내용을 입력하세요."></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><button>작성</button></td>
				</tr>
			</table>
		</form>
		
		<div class="btn">
			<button onclick="history.back();">뒤로가기</button>
		</div>
	</nav>
	
<%@ include file="/view/usr/common/footer.jsp" %>