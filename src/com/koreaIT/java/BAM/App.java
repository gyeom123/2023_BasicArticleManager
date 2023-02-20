package com.koreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Article;
import com.koreaIT.java.BAM.util.Util;

public class App {
	private List<Article> articles;

	App() {
		articles = new ArrayList<>();
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 3;

		makeTestData();

		while (true) {

			System.out.printf("명령어 ) "); // 줄바꿈을 하지않고 한줄에 입력을 받기 위해 printf를 쓴다
			String cmd = sc.nextLine().trim(); // trim() : 맨앞과 맨뒤에 있는 공백을 제거해주는 함수

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}

			if (cmd.equals("exit")) {
				break; // 'cmd'변수안에 있는 문장이 "exit"경우 가장 가까운 반복문을 탈출한다.
			}

			if (cmd.equals("article write")) {

				int id = lastArticleId + 1; // 회원이 가지고 있는 고유번호
				lastArticleId = id;

				System.out.printf("제목 : ");
				String title = sc.nextLine(); // 제목의 변수 : title
				System.out.printf("내용 : ");
				String body = sc.nextLine(); // 내용의 변수 : body

				String get_current_date_time = Util.gettine();
				// Util.gettine() : Util파일에 있는 gettine() ☆현재 날짜시간출력 함수를 실행

				Article article = new Article(id, get_current_date_time, title, body);
				// 입력받은 정보(종이)를 Article(노트)에 넘겨준다

				articles.add(article);
				// 정보를 저장한 article(노트)를 articles(책장)에 넣어서(.add) 정보를 저장하겠다
				// 함수가 끝나면 정보가 사라지는 전역변수이므로 함수가 끝나기 전에 저장

				System.out.printf("%d번게시글이 생성되었습니다\n", id);

			} else if (cmd.equals("article list")) {

				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}

				System.out.println("  번호 ㅣ   제목	ㅣ		날짜	ㅣ	조회수");
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("  %d ㅣ   %s	   ㅣ	%s	ㅣ	%d\n", article.id, article.title,
							article.get_current_date_time, article.views);

				}

			} else if (cmd.startsWith("article detail ")) {
				// startsWith : 입력받은 명령어가 "article detail "로 시작하는지 검사해주는 함수

				String[] cmdBits = cmd.split(" ");// split :(" ")을 기준으로 문장을 나눈다.
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleId(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물이 없습니다.\n", id);
					continue;
				}

				foundArticle.getViewsCnt();

				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.get_current_date_time.substring(0, 10)); // substring : 출력을
																										// 짤라주는 함수
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회수 : %d\n", foundArticle.views);

			} else if (cmd.startsWith("article delete ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleId(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물이 없습니다.\n", id);
					continue;
				}

				articles.remove(articles.indexOf(foundArticle));
				// articles에 입력받은 게시글(id)이 있는방(foundArticle)을 삭제(remove)한다.

				System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);

			} else if (cmd.startsWith("article modify ")) {

				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleId(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물이 없습니다.\n", id);
					continue;
				}

				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;

				System.out.printf("%d번 게시물이 수정되었습니다.\n", id);

			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}

		}

		System.out.println("==프로그램  종류==");

		sc.close();
	}

	private Article getArticleId(int id) {

		for (Article article : articles) {
			if (id == article.id) {
				return article;
			}
		}
		return null;
	}

	private void makeTestData() {
		System.out.println("게시물 테스트 데이터를 생성합니다.");

//		String get_current_date_time = Util.gettine();

//		Article article1 = new Article(1, get_current_date_time, " title1", "body1", 10);
//		Article article2 = new Article(2, get_current_date_time, " title2", "body2", 20);
//		Article article3 = new Article(3, get_current_date_time, " title3", "body3", 30);

		articles.add(new Article(1, Util.gettine(), " 제목1", "내용1", 10));
		articles.add(new Article(2, Util.gettine(), " 제목2", "내용2", 20));
		articles.add(new Article(3, Util.gettine(), " 제목3", "내용3", 30));
	}

}