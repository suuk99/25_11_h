package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;
import com.example.demo.dto.Paging;
import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.util.Util;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	private BoardService boardService;
	
	public UsrArticleController(ArticleService articleService, BoardService boardService) {
		this.articleService = articleService;
		this.boardService = boardService;
	}
	
	@GetMapping("/usr/article/write")
	public String write() {
		return "usr/article/write";
	}
	
	@PostMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpSession session, String title, String content, Integer boardId) {
		// 비동기 방식 처리 해보기
		if (boardId.intValue() == 0) {
			return Util.jsReplace("게시판을 선택해 주세요.", "hb");
		}
		
		if (title.trim().length() == 0 ) {
			return Util.jsReplace("제목을 입력해 주세요.", "hb");
		}
		
		if (content.trim().length() == 0 ) {
			return Util.jsReplace("내용을 입력해 주세요.", "hb");
		}
		
		this.articleService.writeArticle(title, content,(int) session.getAttribute("loginMemberId"), boardId);
		
		int id = this.articleService.getLastInsertId();
		
		return Util.jsReplace(String.format("%d번 게시물이 작성 되었습니다.", id), String.format("detail?id=%d", id));
	}
	
	@GetMapping("/usr/article/list")
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	public String list(Model model, int boardId, Paging paging, int page) {
		
		int totalCount = articleService.getTotalCountBoardId(boardId);
		paging.setTotalCount(totalCount);
		
		List<Article> articles = this.articleService.showList(boardId, paging);
=======
	public String list(Model model, Integer boardId, String keyword) {
		
		if (keyword != null) {
			keyword = keyword.trim();
			
			if (keyword.trim().length() == 0) {
				keyword = null;
			}
		}
		
		if (boardId == null) {
			boardId = 1;
		}

		List<Article> articles = this.articleService.showList(boardId, keyword);
>>>>>>> 2f2db68 (게시판 검색 기능 구현 중)
=======
	public String list(Model model, int boardId, @RequestParam (defaultValue = "1") int cPage) {
=======
	public String list(Model model, int boardId, @RequestParam (defaultValue = "1") int cPage, @RequestParam(required = false) String keyword, @RequestParam(required = false) String searchType) {
		
>>>>>>> temp
		int itemsInAPage = 10;
		int limitFrom = (cPage - 1) * itemsInAPage;
		int articlesCnt = this.articleService.getArticlesCnt(boardId);
		int totalPagesCnt = (int) Math.ceil(articlesCnt / (double) itemsInAPage);
		
		//페이징
		int begin = ((cPage - 1) / 10) * 10 + 1;
		int end = (((cPage - 1) / 10) + 1) * 10;
		
		if (end > totalPagesCnt) {
			end = totalPagesCnt;
		}
		
<<<<<<< HEAD
		List<Article> articles = this.articleService.showList(boardId, limitFrom, itemsInAPage);
>>>>>>> paging
=======
		List<Article> articles = this.articleService.showList(boardId, keyword, searchType, limitFrom, itemsInAPage);
>>>>>>> temp
		String boardName = this.boardService.getBoardNameById(boardId);
		
		model.addAttribute("articles", articles);
		model.addAttribute("boardName", boardName);
<<<<<<< HEAD
<<<<<<< HEAD
		model.addAttribute("paging", paging);
=======
		model.addAttribute("boardId",boardId);
>>>>>>> 2f2db68 (게시판 검색 기능 구현 중)
=======
		model.addAttribute("totalPagesCnt", totalPagesCnt);
		model.addAttribute("articlesCnt", articlesCnt);
		model.addAttribute("begin", begin);
		model.addAttribute("end", end);
		model.addAttribute("cPage", cPage);
<<<<<<< HEAD
>>>>>>> paging
=======
		model.addAttribute("keyword", keyword);
		model.addAttribute("searchType", searchType);
		model.addAttribute("boardId", boardId);
>>>>>>> temp
		
		return "usr/article/list";
	}
	
	@GetMapping("/usr/article/detail")
	public String detail(Model model, int id) {
		
		Article article = this.articleService.getArticleById(id);
		
		model.addAttribute("article", article);
		
		return "usr/article/detail";
	}
	
	@GetMapping("/usr/article/modify")
	public String modify(Model model, int id) {
		
		Article article = this.articleService.getArticleById(id);
		
		model.addAttribute("article", article);
		
		return "usr/article/modify";
	}
	
	@PostMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String content) {

		this.articleService.modifyArticle(id, title, content);
		
		return Util.jsReplace(String.format("%d번 게시물이 수정 되었습니다.", id), String.format("detail?id=%d", id));
	}
	
	@GetMapping("/usr/article/delete")
	@ResponseBody
	public String delete(int id, int boardId) {
		
		this.articleService.deleteArticle(id);
		
		return Util.jsReplace(String.format("%d번 게시물이 삭제 되었습니다.", id), String.format("list?boardId=%d", boardId));
	}
	
}
