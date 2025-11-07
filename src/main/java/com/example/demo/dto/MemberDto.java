package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
	private int id;
	private String loginId;
	private String loginPw;
	private String userName;
	private String sex;
	private String regDate;

}