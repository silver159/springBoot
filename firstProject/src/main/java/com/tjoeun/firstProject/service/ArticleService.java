package com.tjoeun.firstProject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tjoeun.firstProject.dto.ArticleForm;
import com.tjoeun.firstProject.entity.Article;
import com.tjoeun.firstProject.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Service 어노테이션을 붙여준 클래스는 springBoot가 서비스로 인식하여 객체를 자동으로 생성해
// 등록한다.
@Service
public class ArticleService {
	
// ArticleApiControllor 클래스에서 실행했던 데이터베이스 작업으로 서비스 클래스에서 실행한다.	
// JPA를 사용한 데이터베이스 작업을 하기 위해서 생성한 JpaRepository 인터페이스를 상속받아
// 만든 repository 객체를 선언하고 초기화 한다.
	@Autowired
	private ArticleRepository articleRepository;

// 모든 글 목록 얻어오기	
	public List<Article> index() {
		log.info("ArticleService의 index() 메소드 실행");
		return articleRepository.findAll();
	}

// 특정 글 얻어오기	
	public Article show(Long id) {
		log.info("ArticleService의 show() 메소드 실행");
		return articleRepository.findById(id).orElse(null);
	}

// 글저장	
	public Article create(ArticleForm dto) {
		log.info("ArticleService의 create() 메소드 실행");
		// id는 데이터베이스가 자동으로 생성하므로 id가 넘어오는 데이터는 저장하지 않는다.
		Article article = dto.toEntity();
		if(article.getId() != null) {
			return null;
		}
		return articleRepository.save(article);
	}

// 글수정	
	public Article update(Long id, ArticleForm dto) {
		log.info("ArticleService의 update() 메소드 실행");
		Article article = dto.toEntity();
		Article target = articleRepository.findById(id).orElse(null);
		if (target == null) {
			log.info("잘못된 요청! id: {}", id);
			return null;
		}
		
		// id만 넘어오고 title, content가 null이 넘어오면 null을 리턴하는 코드를 추가한다.
		if(article.getTitle() == null && article.getContent() == null) {
			log.info("잘못된 요청!");
			return null;
		}
		
		target.patch(article);
		return articleRepository.save(target);
	}

// 글삭제	
	public Article delete(Long id) {
		log.info("ArticleService의 delete() 메소드 실행");
		Article target = articleRepository.findById(id).orElse(null);
		if (target == null) {
			return null;
		}
		articleRepository.delete(target);
		return target;
	}

// 트랜잭션 => 실패 => 롤백
// @Transactional 어노테이션은 해당 메소드를 트랜잭션으로 묶는다.
	@Transactional
	public List<Article> transactionTest(List<ArticleForm> dtos) {
		log.info("ArticleService의 transactionTest() 메소드 실행");
		
		// dto 묶음을 entity 묶음으로 변환한다.
		/*
		List<Article> articleList = new ArrayList<Article>();
		for (int i=0; i<dtos.size(); i++) {
			Article entity = dtos.get(i).toEntity();
			articleList.add(entity);
		}
		*/
		
		// dto 객체가 저장된 List를 entity로 변환해 Stream으로 변환한 후 다시 List로 변환한다.
		List<Article> articleList = dtos.stream()
				.map(dto -> dto.toEntity())
				.collect(Collectors.toList());
		log.info("articleList: " + articleList);
		
		// entity 묶음을 데이터베이스에 저장한다.
		/*
		for (int i=0; i<articleList.size(); i++) {
			articleRepository.save(articleList.get(i));
		}
		*/
		articleList.stream().forEach(article -> articleRepository.save(article));
		
		// 강제 예외 발생
		articleRepository.findById(-1L).orElseThrow( // L,l은 long 타입임을 알려준다.
			() -> new IllegalArgumentException("결재 실패!!!")	
		);
		
		return articleList;
	}
}
