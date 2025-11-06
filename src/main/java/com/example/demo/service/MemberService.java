package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.dto.Member;

@Service
public class MemberService {
	
	MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	public void joinMember(String loginId, String loginPw, String userName, String sex) {
		this.memberDao.joinMember(loginId, loginPw, userName, sex);
	}

	public Member getMemberLoginId(String loginId) {
		// TODO Auto-generated method stub
		return null;
	}
}