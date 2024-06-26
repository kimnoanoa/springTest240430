package com.ezen.test.handler;

import com.ezen.test.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class PagingHandler {
	
	//리스트 화면 하단의 페이지 조작부 가능을 담당하는 변수들을 생성
	// 시작페이지 번호, 끝페이지번호
	// 진짜 끝번호
	// 이전, 다음생성 여부
	// 전체게시글 수와 현재페이지 번호, 한페이지에 들어갈 게시글수 (매개변수)
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int realEndPage;
	
	private int totalCount;
	private PagingVO pgvo;
	
	
	public PagingHandler(PagingVO pgvo,int totalCount) {
		this.pgvo =pgvo;
		this.totalCount = totalCount;
		
		//pageNo => 1 / 10=> 0.1(올림) => 1*10 => 10
		//pageNo => 2 / 10=> 0.2(올림) => 1*10 => 10
		//pageNo => 9 / 10=> 0.9(올림) => 1*10 => 10
		//pageNo => 11 / 10=> 1.1(올림) => 2*10 => 20
		//
		this.endPage =(int) Math.ceil(pgvo.getPageNo() / (double)10)*10;
		this.startPage = endPage -9;
		
		// 진짜 끝 페이지
		//103 -> 103 / 10 => 10.3 => 11페이지
		
		this.realEndPage =(int) Math.ceil(totalCount/ (double)pgvo.getQty());
		
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
			
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
		
		
		
	}
	

}
