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
	public void joinMember(String loginId, String loginPw, String userName, String sex);


	public void getCheckId(String loginId);

	
	@Select("""
			SELECT * FROM `member`
				WHERE loginId = #{loginId} AND loginPw = #{loginPw}
			""")
	public MemberDto loginMember(String loginId, String loginPw);
		
}