<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="회원가입" />

<%@ include file="/view/usr/common/header.jsp"%>

<script>
	$(document).ready(function() {
    $('input[name="loginId"]').on('blur', function() {
        const loginId = $(this).val().trim(); 

        if (loginId.length === 0) {
            $('#msg').html('아이디를 입력해주세요.');
            return;
        }

		$.ajax({
			url: '/usr/member/checkId',
			type: 'get',
			data: {loginId: loginId},
			dataType: 'text',
			success: function(result) {
				$('#msg').html(result);
			},
			error: function(xhr, status, error) {
				console.log(error);
			}
		});
	});
});    
</script>

<nav class="list">
	<div class="top_name">회원가입</div>
		<form action="/usr/member/doJoin" method="post">
			<table border="1">
				<tr>
					<th>아이디</th>
					<td><input name="loginId" type="text" placeholder="아이디" /><div id="msg"></div></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input name="loginPw" type="password" placeholder="비밀번호" /></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td><input name="checkPw" type="password" placeholder="비밀번호 확인" /></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input name="userName" type="text" placeholder="이름" /></td>
				</tr>
				<tr>
					<th>성별</th>
					<td><select class="box" name="sex">
							<option value="choice">== 성별 ==</option>
							<option value="male">남자</option>
							<option value="female">여자</option>
					</select></td>
				<tr>
					<td colspan="2"><button>가입</button></td>
				</tr>
			</table>
		</form>
	<div class="btn">
		<div>
			<button onclick="history.back();">뒤로가기</button>
		</div>
	</div>
</nav>
<%@ include file="/view/usr/common/footer.jsp"%>