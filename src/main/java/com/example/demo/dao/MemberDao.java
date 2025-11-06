package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
		
}