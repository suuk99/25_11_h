package com.example.demo.controller;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Article;
import com.example.demo.dto.MemberDto;
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
        if (boardId.intValue() == 0) {
            return Util.jsReplace("게시판을 선택해 주세요.", "hb");
        }

        if (title.trim().length() == 0) {
            return Util.jsReplace("제목을 입력해 주세요.", "hb");
        }

        if (content.trim().length() == 0) {
            return Util.jsReplace("내용을 입력해 주세요.", "hb");
        }

        this.articleService.writeArticle(title, content, (int) session.getAttribute("loginMemberId"), boardId);
        int id = this.articleService.getLastInsertId();
        return Util.jsReplace(String.format("%d번 게시물이 작성 되었습니다.", id),
                String.format("detail?id=%d", id));
    }

    @GetMapping("/usr/article/list")
    public String list(Model model, int boardId,
                       @RequestParam(defaultValue = "1") int cPage,
                       String searchType,
                       @RequestParam(defaultValue = "") String keyword
                       ) {

        int itemsInAPage = 10;
        int limitFrom = (cPage - 1) * itemsInAPage;

        int articlesCnt = this.articleService.getArticlesCnt(boardId, keyword, searchType);
        int totalPagesCnt = (int) Math.ceil(articlesCnt / (double) itemsInAPage);

        int begin = ((cPage - 1) / 10) * 10 + 1;
        int end = (((cPage - 1) / 10) + 1) * 10;
        if (end > totalPagesCnt) {
            end = totalPagesCnt;
        }

        List<Article> articles = this.articleService.showList(boardId, keyword, searchType, limitFrom, itemsInAPage);
        String boardName = this.boardService.getBoardNameById(boardId);

        model.addAttribute("articles", articles);
        model.addAttribute("boardName", boardName);
        model.addAttribute("totalPagesCnt", totalPagesCnt);
        model.addAttribute("articlesCnt", articlesCnt);
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
        model.addAttribute("cPage", cPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchType", searchType);
        model.addAttribute("boardId", boardId);

        return "usr/article/list";
    }

    @GetMapping("/usr/article/detail")
    public String detail(Model model, int id, HttpSession session) {
    	
    	MemberDto loginMember = (MemberDto) session.getAttribute("loginMember");
    	Integer memberId = loginMember != null ? loginMember.getId() : null;
    	
    	boolean hasLiked = false;
    	if (memberId != null) {
    		hasLiked = articleService.hasLiked(id, memberId);
    	}
    	
    	int likeCount = articleService.getLikeCount(id);
    	
        Article article = this.articleService.getArticleById(id);
        
        model.addAttribute("article", article);
        model.addAttribute("articleId", id);
        model.addAttribute("hasLiked", hasLiked);
        model.addAttribute("likeCount", likeCount);
        
        return "usr/article/detail";
    }
    
    @PostMapping("/usr/article/toggleLike")
    @ResponseBody
    public Map<String, Object> toggleLike(int articleId, HttpSession session) {
    	
    	Integer loginMemberId = (Integer) session.getAttribute("loginMemberId");
    	
    	Map<String, Object> result = new HashMap<>();
    	
    	if (loginMemberId == null) {
    		result.put("status", "fail");
    		return result;
    	} 
    	
    	boolean hasLiked = articleService.hasLiked(articleId, loginMemberId);
    	
    	if (hasLiked) {
    		articleService.cancleLike(articleId, loginMemberId);
    	} else {
    		articleService.addLike(articleId, loginMemberId);
    	}
    	
    	int likeCount = articleService.getLikeCount(articleId);
    	
    	result.put("status", "success");
    	result.put("liked", !hasLiked);
    	result.put("likeCount", likeCount);
    	
    	return result;
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
        return Util.jsReplace(String.format("%d번 게시물이 수정 되었습니다.", id),
                String.format("detail?id=%d", id));
    }

    @GetMapping("/usr/article/delete")
    @ResponseBody
    public String delete(int id, int boardId) {
        this.articleService.deleteArticle(id);
        return Util.jsReplace(String.format("%d번 게시물이 삭제 되었습니다.", id),
                String.format("list?boardId=%d", boardId));
    }

}
