package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dto.Article;

@Service
public class ArticleService {

	private ArticleDao articleDao;
	
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
	public void writeArticle(String title, String content, int loginMemberId, int boardId) {
		this.articleDao.writeArticle(title, content, loginMemberId, boardId);
	}

	public List<Article> showList(int boardId, String keyword, String searchType, int limitFrom, int itemsInAPage) {
		return this.articleDao.showList(boardId, keyword, searchType, limitFrom, itemsInAPage);
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

	public int getArticlesCnt(int boardId) {
		return this.articleDao.getArticlesCnt(boardId);
	}

	public int getGoodPoint(int loginedMemberId, int id) {
		return this.articleDao.getGoodPoint(loginedMemberId, id);
	}

	public int getGoodCnt(int id) {
		return this.articleDao.getGoodCnt(id);
	}

	public void goodAdd(int id, int loginedMemberId) {
		this.articleDao.goodAdd(id, loginedMemberId);
	}

	public void goodRemove(int id, int loginedMemberId) {
		this.articleDao.goodRemove(id, loginedMemberId);
	}

}
