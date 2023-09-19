package com.tjoeun.firstProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tjoeun.firstProject.dto.CommentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
public class Comment {
	
	@Id // 기본키
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 시퀀스를 데이터베이스가 
	// 자동으로 증가시킨다.
	private Long id;
	@Column
	private String nickname;
	@Column
	private String body;
	
	// 외래키 설정
	@ManyToOne // 댓글 엔티티 여러개가 하나의 메인글(Article)에 연관된다.
	@JoinColumn(name = "article_id") // article_id 컬럼에 Article의 대표값(기본키)을 저장한다.
	private Article article;
	
// CommentDto를 entity로 변환하는 메소드(댓글, 메인글)
	public static Comment createComment(CommentDto dto, Article article) {
		log.info("Comment의 createComment() 메소드 실행");
		// 댓글의 id는 데이터베이스가 자동으로 붙여주기 때문에 id가 넘어오는 경우 예외를 
		// 발생시킨다.
		if(dto.getId() != null) {
			throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
		}
		
		// 댓글을 생성하기 위해 요청한 id가 데이터베이스에 저장된 id와 다를 경우 예외를 
		// 발생시킨다.
		if(dto.getArticleId() != article.getId()) {
			throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
		}
		
		// entity 생성 및 반환
		return new Comment(dto.getId(), dto.getNickname(), dto.getBody(), article);
	}

// 댓글을 갱신하는 메소드	
	public void patch(CommentDto dto) {
		log.info("Comment의 patch() 메소드 실행");
		// 댓글을 수정하기 위해 요청한 id가 데이터베이스에 저장된 id와 다를 경우 예외를 
		// 발생시킨다.
		if(this.id != dto.getId()) {
			throw new IllegalArgumentException("댓글 수정 실패! 댓글의 id가 잘못되었습니다.");
		}
		// 댓글 수정
		if(dto.getNickname() != null) {
			this.nickname = dto.getNickname();
		}
		if(dto.getBody() != null) {
			this.body = dto.getBody();
		}
	}
}
