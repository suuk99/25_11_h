package com.example.demo.dto;

import java.io.IOException;

import com.example.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;

public class Req {
	
	@Getter
	private int loginMemberId;
	private HttpServletResponse resp;
	
	public Req(HttpServletRequest req, HttpServletResponse resp) {
		
		this.resp = resp;
		
		HttpSession session = req.getSession();
		
		int loginMemberId = -1;
		
		if (session.getAttribute("loginMemberId") != null) {
			loginMemberId = (int) session.getAttribute("loginMemberId");
		}
		
		this.loginMemberId = loginMemberId;
	}
	
	public void jsPrintReplace(String msg, String url) {
		
		this.resp.setContentType("text/html; charset=UTF-8");
		
		try {
			this.resp.getWriter().append(Util.jsReplace(msg, url));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
