package com.tjoeun.firstProject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// JPA를 사용해서 테이블로 매핑할 클래스는 @Entity 어노테이션을 필수로 붙여야 하고 엔티티라 
// 부른다.
// @Entity 어노테이션이 붙은 클래스 이름으로 springBoot가 자동으로 테이블을 만들고 클래스 
// 내부에 선언한 필드 이름으로 테이블을 구성하는 컬럼(필드)을 만들어 준다.
@Entity // 현재 클래스는 Entity로 사용되는 클래스임을 의미한다.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Article {
	
	// 기본키로 사용할 필드 선언
	// 기본키를 자동으로 생성하려면 @Id와 @GeneratedValue 어노테이션을 함께 사용해야 한다.
	@Id // 필드를 기본키로 설정한다.
//	@GeneratedValue // 기본키 값을 자동으로 생성한다.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 시퀀스를 자동으로 증가하게 한다.
	private Long id; // null을 받을 수 있게 Long 래퍼 클래스(Wrapper Class)로
	// 데이터를 저장할 필드 선언
	@Column // 필드를 테이블 컬럼과 매핑한다.
	private String title;
	@Column
	private String content;

/*	
	public Article() {}

	public Article(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
*/
	
// 넘어오지 않은 데이터를 기존 데이터로 채워주는 메소드	
	public void patch(Article article) {
		if(article.title != null) {
			this.title = article.title;
		}
		if(article.content != null) {
			this.content = article.content;
		}
	}
	
}
