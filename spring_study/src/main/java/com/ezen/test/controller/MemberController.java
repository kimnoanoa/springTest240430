package com.ezen.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.test.domain.MemberVO;
import com.ezen.test.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/member/*")
@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService msv; 
	
	
	
	
	@GetMapping("/register")
	public void register() {	
	}
	
	@PostMapping("/register")
	public String insert(MemberVO mvo) {
		log.info(">>>mvo객체 확인>>>>{}",mvo);
		
		int isOk =  msv.insert(mvo);
		
		log.info(">>>>>회원가입 "+ (isOk>0?"성공!!":"실패 ㅠㅠ"));
		
		return "index";
	}
	
	@GetMapping("/login")
	public void login() {	
	}
	
	@PostMapping("/login")
	public String login(MemberVO mvo, HttpServletRequest request, Model m) {
		
		log.info(">>>mvo객체 확인>>>>{}",mvo);
		
		//mvo 객체가 DB의 값과 일치하는 객체 가져오기
		MemberVO loginMvo = msv.isUser(mvo);
		log.info(">>>Login Mvo 확인>>>>{}",loginMvo);
		
		if(loginMvo != null) {
			//로그인 성공했을시
			
			HttpSession ses = request.getSession(); 
			ses.setAttribute("ses", loginMvo);
			ses.setMaxInactiveInterval(10*60);
		}else {
			//로그인 실패시.
			m.addAttribute("msg_login", "fail");
		}
		
		return "index";
	}
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request, Model m) {
		//라스트 로그인 업데이트 => 세션 객체 삭제 => 객체 끊기
		
		MemberVO mvo = ((MemberVO)request.getSession().getAttribute("ses"));
		
		msv.lastLoginUpdate(mvo);
		
		request.getSession().removeAttribute("ses");
		request.getSession().invalidate();
		
		String msg_remove = (String)m.getAttribute("msg_remove");
		log.info(">>>>>msg_remove>>>>{}",msg_remove);
		
		if(msg_remove == null) {			
		m.addAttribute("msg_logout", "ok");
		}
		
		return "index";
		
	}
	
	@GetMapping("/modify")
	public void modify() {
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO mvo) {
		
		log.info(">>>>수정 mvo 객체>>>>>{}",mvo);
		
		msv.modify(mvo);
		
		return "redirect:/member/logout";
	}
	
	@GetMapping("/remove")
	public String remove(RedirectAttributes re) {
		
		msv.remove();
		
		//add flush는 갔다가 사라진다.
		re.addFlashAttribute("msg_remove","1");
		
		return "redirect:/member/logout";
	}

}
