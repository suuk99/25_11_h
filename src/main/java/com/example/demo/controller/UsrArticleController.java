package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;
import com.example.demo.service.ArticleService;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@GetMapping("/usr/article/write")
	@ResponseBody
	public String write(String title, String content) {
		
		this.articleService.writeArticle(title, content);
		
		return "글 작성 완료";
	}
	
	@GetMapping("/usr/article/list")
	public String list(Model model) {
		
		List<Article> articles = this.articleService.showList();
		
		model.addAttribute("articles", articles);
		
		return "usr/article/list";
	}
	
	@GetMapping("/usr/article/detail")
	@ResponseBody
	public Object detail(int id) {
		
		Article article = this.articleService.getArticleById(id);
		
		if (article == null) {
			return "그 번호에 해당하는 글은 없어";
		}
		
		return article;
	}
	
	@GetMapping("/usr/article/modify")
	@ResponseBody
	public String modify(int id, String title, String content) {
		
		this.articleService.modifyArticle(id, title, content);
		
		return "수정 완료";
	}
	
	@GetMapping("/usr/article/delete")
	@ResponseBody
	public String delete(int id) {
		
		this.articleService.deleteArticle(id);
		
		return "삭제 완료";
	}
	
}
