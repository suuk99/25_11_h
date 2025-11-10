<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="회원가입" />

<%@ include file="/view/usr/common/header.jsp"%>

<script>
	let checkLogin = null;
	const joinSubmit = function(form) {
		form.loginId.value = form.loginId.value.trim();
		form.loginPw.value = form.loginPw.value.trim();
		form.checkPw.value = form.checkPw.value.trim();
		form.userName.value = form.userName.value.trim();
		
		if (form.loginId.value.length == 0) {
			alert('아이디를 입력하세요.');
			form.loginId.focus();
			return false;
		}
		
		if (form.loginId.value != checkLogin) {
			alert('이미 사용중인 아이디 입니다.');
			form.loginId = '';
			form.loginId.focus();
			return false;
		}
		
		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력하세요.');
			form.loginPw.focus();
			return false;
		}
		
		if (form.checkPw.value.length == 0) {
			alert('비밀번호가 일치한지 확인해 주세요.');
			form.checkPw.focus();
			return false;
		}
		
		if (form.loginPw.value != form.checkPw.value) {
			alert('비밀번호가 일치하지 않습니다.');
			form.loginPw.value = '';
			form.checkPw.value = '';
			form.loginPw.focus();
			return false;
			
		}
		
		if (form.userName.value.length == 0) {
			alert('이름을 입력하세요.');
			form.userName.focus();
			return false;
		}
		
		if (form.sex.value == 'choice') {
			alert('성별을 선택해 주세요.');
			form.sex.focus();
			return false;
		}
		return true;
	}
	const checkId = function(el) {
		
		el.value = el.value.trim();
		let msg = $('#msg');
		
		if (el.value.length == 0) {
			msg.addClass('text-red-500');
			msg.removeClass('text-green-500');
			msg.html('아이디를 입력하세요.');
			return;
		}
		
		$.ajax({
			url: '/usr/member/checkId',
			type: 'get',
			data: {loginId:el.value},
			dataType: 'json',
			success: function(data) {
				if (data.isSuccess) {
					msg.removeClass('text-red-500');
					msg.addClass('text-green-500');
					msg.html(`${data.rsMsg}`);
					checkLogin = el.value;
				} else {
					msg.removeClass('text-green-500');
					msg.addClass('text-red-500');
					msg.html(`${data.rsMsg}`);
					checkLogin = null;
				}
			},
			error: function(xhr, status, error) {
				console.log(error);
			}
		})
	}

</script>

<nav class="list">
	<div class="top_name">회원가입</div>
	<form action="/usr/member/doJoin" method="post" onsubmit="return joinSubmit(this)">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input name="loginId" type="text" placeholder="아이디"
					onblur="checkId(this)" />
					<div id="msg" class="mt-1 text-sm h-1 text-left"></div></td>
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