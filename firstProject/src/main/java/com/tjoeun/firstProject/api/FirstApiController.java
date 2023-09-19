package com.tjoeun.firstProject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// REST API용으로 사용할 컨트롤러는 @Controller 어노테이션이 아닌 @RestController 어노테이션을
// 붙여 선언한다.
// @Controller 어노테이션이 붙은 컨트롤러는 뷰페이지를 반환하지만 @RestController 어노테이션이
// 붙은 컨트롤러는 JSON을 반환한다.
@RestController
public class FirstApiController {
	
	@GetMapping("/api/hello")
	public String hello() {
		return "hello world";
	}
	
}
