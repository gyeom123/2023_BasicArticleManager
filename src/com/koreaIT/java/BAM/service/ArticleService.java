package com.koreaIT.java.BAM.service;

import java.util.List;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dao.ArticleDao;
import com.koreaIT.java.BAM.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	// 고유번호
	public int getLastId() {
		return articleDao.getLastId();
	}

	// 게시글 추가
	public void add(Article article) {
		articleDao.add(article);
	}

	// 게시글 상세보기 함수
	public List<Article> getPrintArticles(String searchKeyword) {
		return articleDao.getPrintArticles(searchKeyword);
	}

	// id(사용자가 원하는 검색어)로 해당 하는 인덱스 넘기는 함수
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	// 사용자가 원하는 게시글 삭제
	public void ArticleRemove(Article foundArticle) {
		articleDao.ArticleRemove(foundArticle);
	}

	public void ArticleModify(Article foundArticle, String title, String body) {
		articleDao.ArticleModify(foundArticle, title, body);

	}

}
