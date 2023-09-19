package com.tjoeun.firstProject.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.tjoeun.firstProject.dto.ArticleForm;
import com.tjoeun.firstProject.entity.Article;

// @SpringBootTest 어노테이션을 붙여 스프링 부트와 연동한 통합 테스트를 수행한다.
@SpringBootTest
class ArticleServiceTest {
	
	@Autowired
	ArticleService articleService;
	
	@Test
	void testIndex() {
		// 예상
		Article article1 = new Article(1L, "홍길동", "천재");
		Article article2 = new Article(2L, "임꺽정", "바보");
		Article article3 = new Article(3L, "장길산", "멍청이");
		List<Article> expected = new ArrayList<Article>(Arrays.asList(article1, article2, article3));
		// 실제
		List<Article> articles = articleService.index();
		// 비교
		assertEquals(expected.toString(), articles.toString());
	}

	@Test
	void testShow성공_존재하는_id() {
		// 예상
		Long id = 1L;
		Article expected = new Article(id, "홍길동", "천재");
		// 실제
		Article article = articleService.show(id);
		// 비교
		assertEquals(expected.toString(), article.toString());
	}
	
	@Test
	void testShow실패_존재하지_않는_id() {
		// 예상
		Long id = -1L;
		Article expected = null;
		// 실제
		Article article = articleService.show(id);
		// 비교
		assertEquals(expected, article);
	}

// 테이블이 변경되는 테스트를 실행하는 경우 이전 테스트의 영향을 받아서 하나씩 테스트 할 때는
// 정상적으로 실행되던 테스트가 오류가 발생할 수 있기 때문에 테스트 결과가 테이블을
// 변경시키는 테스트는 @Transactional 어노테이션을 붙여서 테스트가 끝나면 롤백하도록 해줘야
// 한다.
	@Test
// @Test에 @Transactional를 추가하면 테스트 종료 후 변경된 데이터를 롤백(처음으로 되돌림)
// 처리를 한다.
	@Transactional
	void testCreate_성공_title과_content만_있는_dto_입력() {
		// 예상
		String title = "손오공";
		String content = "수리수리마수리";
		ArticleForm dto = new ArticleForm(null, title, content);
		Article expected = new Article(4L, title, content);
		// 실제
		Article article = articleService.create(dto);
		// 비교
		assertEquals(expected.toString(), article.toString());
	}

	@Test
	@Transactional
	void testCreate_실패_id가_포함된_dto_입력() {
		// 예상
		String title = "손오공";
		String content = "수리수리마수리";
		ArticleForm dto = new ArticleForm(4L, title, content);
		Article expected = null;
		// 실제
		Article article = articleService.create(dto);
		// 비교
		assertEquals(expected, article);
	}
	
	@Test
	@Transactional
	void testUpdate_성공_존재하는_id와_title_content가_있는_dto() {
		// 예상
		Long id = 3L;
		String title = "손오공";
		String content = "수리수리마수리";
		ArticleForm dto = new ArticleForm(id, title, content);
		Article expected = new Article(id, title, content);
		// 실제
		Article article = articleService.update(id, dto);
		// 비교
		assertEquals(expected.toString(), article.toString());
	}
	
	@Test
	@Transactional
	void testUpdate_성공_존재하는_id와_title만_있는_dto() {
		// 예상
		Long id = 3L;
		String title = "손오공";
		String content = null;
		ArticleForm dto = new ArticleForm(id, title, content);
		Article expected = new Article(id, title, "멍청이");
		// 실제
		Article article = articleService.update(id, dto);
		// 비교
		assertEquals(expected.toString(), article.toString());
	}
	
	@Test
	@Transactional
	void testUpdate_성공_존재하는_id와_content만_있는_dto() {
		// 예상
		Long id = 3L;
		String title = null;
		String content = "수리수리마수리";
		ArticleForm dto = new ArticleForm(id, title, content);
		Article expected = new Article(id, "장길산", content);
		// 실제
		Article article = articleService.update(id, dto);
		// 비교
		assertEquals(expected.toString(), article.toString());
	}
	
	@Test
	@Transactional
	void testUpdate_실패_존재하지_않는_id의_dto_입력() {
		// 예상
		Long id = 4L;
		String title = "홍길동";
		String content = "수리수리마수리";
		ArticleForm dto = new ArticleForm(id, title, content);
		Article expected = null;
		// 실제
		Article article = articleService.update(id, dto);
		// 비교
		assertEquals(expected, article);
	}
	
	@Test
	@Transactional
	void testUpdate_실패_id만_있는_dto_입력() {
		// 예상
		Long id = 3L;
		String title = null;
		String content = null;
		ArticleForm dto = new ArticleForm(id, title, content);
		Article expected = null;
		// 실제
		Article article = articleService.update(id, dto);
		// 비교
		assertEquals(expected, article);
	}

	@Test
	@Transactional
	void testDelete_성공_존재하는_id_입력() {
		// 예상
		Long id = 3L;
		String title = "장길산";
		String content = "멍청이";
		Article expected = new Article(id, title, content);
		// 실제
		Article article = articleService.delete(id);
		// 비교
		assertEquals(expected.toString(), article.toString());
	}
	
	@Test
	@Transactional
	void testDelete_실패_존재하지_않는_id_입력() {
		// 예상
		Long id = 4L;
		Article expected = null;
		// 실제
		Article article = articleService.delete(id);
		// 비교
		assertEquals(expected, article);
	}

}
