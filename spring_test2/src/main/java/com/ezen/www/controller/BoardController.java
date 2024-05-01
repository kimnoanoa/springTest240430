package com.ezen.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequestMapping("/board/*")
@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService bsv;
	
	 @GetMapping("/register")
	   public String register() {
	      return "/board/register";
	   }
	 
	 @PostMapping("/insert")
	 public String insert(BoardVO bvo) {
		 log.info(">>>> bvo >>>{}",bvo);
		 int isOk = bsv.insert(bvo);
		 return "index";
		 
	 }
	 @GetMapping("/list")
	 public String list(Model m) { //model : 데이터 갖다주는 객체.
		 
		 
		 List<BoardVO> list = bsv.getList();
		 
		 m.addAttribute("list",list);
		 return "/board/list";
	 }
	 @GetMapping({"/detail","/modify"})
	 public void detail(@RequestParam("bno")int bno,Model m) {
		 log.info(">>>>> bno >>>{}",bno);
		 BoardVO bvo =bsv.getDetail(bno);
		 m.addAttribute("bvo",bvo);
		
		 
	 }
	 @PostMapping("/modify")
	 public String modify(BoardVO bvo) {
		 
		 int isOk = bsv.update(bvo);
		 return "redirect:/board/detail?bno="+bvo.getBno();
	 }
	 
	 @GetMapping("/remove")
	 public String remove(@RequestParam("bno")int bno) {
		 bsv.remove(bno);
			return "redirect:/board/list";
	 }
	
}
