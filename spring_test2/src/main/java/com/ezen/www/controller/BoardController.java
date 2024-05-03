package com.ezen.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.handler.FileHandler;
import com.ezen.www.handler.PagingHandler;
import com.ezen.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequestMapping("/board/*")
@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService bsv;
	private final FileHandler fh;
	
	 @GetMapping("/register")
	   public String register() {
	      return "/board/register";
	   }
	 
	 @PostMapping("/insert")
	 public String insert(BoardVO bvo , @RequestParam(name="files", required=false)MultipartFile[] files) {
		 log.info(">>>> bvo >>>{}",bvo);
		 List<FileVO> flist = null;
		 if(files[0].getSize()>0) {
			 //파일이 있다면..
			 flist = fh.uploadFiles(files);
			 
		 }
		 BoardDTO bdto = new BoardDTO(bvo,flist);
		 
		 int isOk = bsv.insert(bdto);
		 return "redirect:/board/list";
		 
	 }
	 @GetMapping("/list")
	 public String list(Model m, PagingVO pgvo) { //model : 데이터 갖다주는 객체.
		 log.info(">>>> pagingVO >>>>{}",pgvo);
		 
		 //페이징 처리 추가
		 
		 List<BoardVO> list = bsv.getList(pgvo);
		 
		 int totalCount = bsv.getTotal(pgvo);
		 PagingHandler ph = new PagingHandler(pgvo,totalCount);
		 
		 m.addAttribute("list",list);
		 m.addAttribute("ph",ph);
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
