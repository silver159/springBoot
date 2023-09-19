package com.tjoeun.firstProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 이 클래스는 컨트롤러임을 나타낸다.
public class FirstController {
	
// 브라우저에 "/hi"라는 요청이 들어오면 niceToMeetYou() 메소드가 실행된다.
	@GetMapping("/hi")
	// 컨트롤러에서 뷰페이지로 데이터를 전달하기 위해서 Model 인터페이스 객체를 사용한다.
	public String niceToMeetYou(Model model) {
		// model 인터페이스 객체에 addAttribute() 메소드로 뷰페이지로 넘겨줄 데이터를 
		// 넣어준다.
		model.addAttribute("username", "홍길동");
		return "greetings"; // viewpage 이름
	}
	
	@GetMapping("/bye")
	public String seeYooNext(Model model) {
		model.addAttribute("nickname", "임꺽정");
		return "goodbye";
	}
}
