package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.dto.MemberDto;

@Service
public class MemberService {
	
	MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void joinMember(String loginId, String loginPw, String userName, String sex) {
		this.memberDao.joinMember(loginId, loginPw, userName, sex);
	}

	public MemberDto getMemberLoginId(String loginId) {
		return null;
	}

	public MemberDto getCheckId(String loginId) {
		this.memberDao.getCheckId(loginId);
	}

	public void loginMember(String loginId, String loginPw) {
		this.memberDao.loginMember(loginId, loginPw);
	}
}