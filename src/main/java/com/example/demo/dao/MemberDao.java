package com.example.demo.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.MemberDto;

@Mapper
public interface MemberDao {
	
	@Insert("""
			INSERT INTO `member`
				SET loginId = #{loginId},
					loginPw = #{loginPw},
					userName = #{userName},
					sex = #{sex},
					regDate = NOW()
			""")
	void joinMember(String loginId, String loginPw, String userName, String sex);

	// 아이디 중복 확인용
	@Select("""
			SELECT * FROM `member`
			WHERE loginId = #{loginId}
			""")
	MemberDto getCheckId(String loginId);
	
	
	
	// 아이디로 회원 조회
	@Select("""
			SELECT * FROM `member`
			WHERE loginId = #{loginId}
			""")
	MemberDto getMemberLoginId(String loginId);
	
	// 로그인용 -> 아이디랑 비번 일치한지 확인
	@Select("""
			SELECT * FROM `member`
			WHERE loginId = #{loginId} AND loginPw = #{loginPw}
			""")
	MemberDto loginMember(String loginId, String loginPw);
}
