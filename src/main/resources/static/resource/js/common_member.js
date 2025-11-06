const submitFormChk_mb = function(form) {
	form.loginId.value = form.loginId.value.trim();
	form.loginPw.value = form.loginPw.value.trim();
	form.checkPw.value = form.checkPw.value.trim();
	form.userName.value = form.userName.value.trim();
	form.birthDate.value = form.birthDate.value.trim();

	if (form.loginId.value.length == 0) {
		alert('아이디를 입력해 주세요.');
		form.loginId.focus();
		return false;
	}

	if (form.loginPw.value.length == 0) {
		alert('비밀번호를 입력해 주세요.');
		form.loginPw.focus();
		return false;
	}
	
	if (form.checkPw.value.length == 0) {
		alert('비밀번호 확인이 진행되지 않았습니다.')
		form.checkPw.focus();
		return false;
	}

	if (form.userName.value.length == 0) {
		alert('이름을 입력해 주세요.');
		form.userName.focus();
		return false;
	}

	if (form.birthDate.value.length == 0) {
		alert('생년월일을 입력해 주세요.');
		form.birthDate.focus();
		return false;
	}

	if (form.sex.value == 'choice') {
		alert('성별을 선택해 주세요.');
		form.sex.focus();
		return false;
	}

	return true;
}


