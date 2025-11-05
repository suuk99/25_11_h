<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="작성"/>

<%@ include file="/view/usr/common/header.jsp" %>

	<div>글쓰기</div>
	
	<form action="/usr/article/doWrite" method="post">
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input name="title" type="text"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><button>작성</button></td>
			</tr>
		</table>
	</form>
	
	<div>
		<button onclick="history.back();">뒤로가기</button>
	</div>
	
<%@ include file="/view/usr/common/footer.jsp" %>