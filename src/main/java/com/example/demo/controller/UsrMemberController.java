package com.example.demo.controller;
import java.lang.reflect.Member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.MemberDto;
import com.example.demo.dto.ResultData;
import com.example.demo.service.ArticleService;
import com.example.demo.service.MemberService;
import com.example.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("usr/member/join")
	public String join() {
		return "usr/member/join";
	}
	
	@PostMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String checkPw, String userName, String sex) {
		
		MemberDto member = this.memberService.getMemberLoginId(loginId);
		
		if (loginId.trim().length() == 0) {
			return Util.jsReplace("아이디를 입력해 주세요.", "hb");
		}
		
		if (member != null) {
			return Util.jsReplace("이미 사용중인 아이디입니다.", "hb");
		}
		
		if (loginPw.trim().length() == 0) {
			return Util.jsReplace("비밀번호를 입력해주세요", "hb");
		}
		
		if (!loginPw.equals(checkPw)) {
			return Util.jsReplace("비밀번호가 일치하지 않습니다", "hb");
		}
		
		if (userName.trim().length() == 0) {
			return Util.jsReplace("이름을 입력해주세요", "hb");
		}
		
		if (sex.equals("choice")) {
			return Util.jsReplace("성별을 선택해 주세요.", "hb");
		}
		
		this.memberService.joinMember(loginId, loginPw, userName, sex);
		
		return Util.jsReplace("회원가입이 완료 되었습니다.", "/"); 
	}
	
	@GetMapping("/usr/member/checkId")
	@ResponseBody
	public ResultData checkId(String loginId) {
		
		MemberDto member = this.memberService.getCheckId(loginId);
		
		if (member != null) {
			return new ResultData<>("F-1", "이미 사용중인 아이디 입니다.");
		}
		return new ResultData<>("S-1", "사용 가능한 아이디 입니다.");
	}
	
	
	
	@GetMapping("/usr/member/login")
	public String login() {
		
		return "/usr/member/login";
	}
	
	@PostMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(String loginId, String loginPw, HttpSession session) {
		
		if (loginId.trim().length() == 0) {
			return Util.jsReplace("아이디를 입력해 주세요.", "hb");
		}
		
		if (loginPw.trim().length() == 0) {
			return Util.jsReplace("비밀번호를 입력해 주세요.","hb");
		}
		
		// 아이디 존재 여부 확인
		MemberDto member = this.memberService.getMemberLoginId(loginId);
		if (member == null) {
			return Util.jsReplace("존재하지 않는 아이디 입니다.", "hb");
		}
		
		// 로그인 검증
		MemberDto loginMember = this.memberService.loginMember(loginId, loginPw);
		if (loginMember == null) {
			return Util.jsReplace("비밀번호가 일치하지 않습니다.", "hb");
		}
		
		// 로그인 성공 시 세션에 저장
		session.setAttribute("loginMemberId", loginMember.getId());
		session.setAttribute("loginMemberName", loginMember.getUserName());
		session.setAttribute("loginMember", loginMember);
		return Util.jsReplace(null, "/");
	}
	
	@GetMapping("/usr/member/logout")
	@ResponseBody
	public String logout(HttpSession session) {
		session.invalidate();
		return Util.jsReplace("로그아웃이 완료 되었습니다.", "/");
	}
	

}