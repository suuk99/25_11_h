<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="로그인" />
    
<%@ include file="/view/usr/common/header.jsp"%>


<nav class ="list">
	<div class="top_name">로그인</div>
	<form action="/usr/member/doLogin" method="post">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input name="loginId" type="text" placeholder="아이디" /></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input name="loginPw" type="password" placeholder="비밀번호" /></td>
			</tr>
			<tr>
				<td colspan="2"><button>로그인</button></td>
			</tr>
		</table>
	</form>
	<div class="btn">
		<button onclick="history.back()">뒤로가기</button>
	</div>
</nav>


<%@ include file="/view/usr/common/footer.jsp"%>
