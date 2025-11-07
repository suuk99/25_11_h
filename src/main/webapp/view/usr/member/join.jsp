<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="회원가입" />

<%@ include file="/view/usr/common/header.jsp"%>

<script>
	const checkId = function(el) {
		el.value = el.value.trim();
		
		let msg = $('#msg');
		// 여러번 사용될 걸 대비해 변수 저장
		
		if (el.value.length == 0) {
			$('#msg').addClass('text-red-500');
			// 폰트 색상 변경을 위해서 클래스를 추가함
			$('#msg').html('아이디를 입력해 주세요.');
			return;
		}
		
		// 비동기 시작
		$.ajax({
			url: '/usr/member/checkId',
			type: 'get',
			data: {loginId:el.value},
			dataType: 'json',
			success: function(data) {
				console.log(data);				
			},
			error: function(xhr, status, error) {
				console.log(error);
;			}
		})
	}


</script>

<nav class="list">
	<div class="top_name">회원가입</div>
	<form action="/usr/member/doJoin" method="post">
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