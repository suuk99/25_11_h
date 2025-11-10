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
		return this.memberDao.getMemberLoginId(loginId);
	}

	public MemberDto getCheckId(String loginId) {
		return this.memberDao.getCheckId(loginId);
	}

	public MemberDto loginMember(String loginId, String loginPw) {
		return this.memberDao.loginMember(loginId, loginPw);
	}
}