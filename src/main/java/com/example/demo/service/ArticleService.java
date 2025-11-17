package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dto.Article;
import com.example.demo.dto.Paging;

@Service
public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	public void writeArticle(String title, String content, int loginMemberId, int boardId) {
		this.articleDao.writeArticle(title, content, loginMemberId, boardId);
	}

<<<<<<< HEAD
	public List<Article> showList(int boardId, Paging paging) {
		return this.articleDao.showList(boardId, paging);
=======
	public List<Article> showList(int boardId, String keyword) {
		return this.articleDao.showList(boardId, keyword);
>>>>>>> 2f2db68 (게시판 검색 기능 구현 중)
	}

	public Article getArticleById(int id) {
		return this.articleDao.getArticleById(id);
	}

	public void modifyArticle(int id, String title, String content) {
		this.articleDao.modifyArticle(id, title, content);
	}

	public void deleteArticle(int id) {
		this.articleDao.deleteArticle(id);
	}

	public int getLastInsertId() {
		return this.articleDao.getLastInsertId();
	}

	public int getTotalCountBoardId(int boardId) {
		// TODO Auto-generated method stub
		return this.articleDao.getTotalCountBoardId(boardId);
	}

}
