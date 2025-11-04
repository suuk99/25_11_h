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
	
	public void writeArticle(String title, String content) {
		this.articleDao.writeArticle(title, content);
	}

	public List<Article> showList() {
		return this.articleDao.showList();
	}

	public Article getArticleById(int id) {
		return this.articleDao.getArticleById(id);
	}

	public void modifyArticle(Article article, String title, String content) {
		this.articleDao.modifyArticle(article, title, content);
	}

	public void deleteArticle(Article article) {
		this.articleDao.deleteArticle(article);
	}

}
