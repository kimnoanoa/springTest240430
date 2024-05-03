package com.ezen.www.handler;

import java.util.List;

import com.ezen.www.domain.CommentVO;
import com.ezen.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class PagingHandler {
	
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int totalCount;
	private PagingVO pgvo;
	
	private int realEndPage;
	
	private List<CommentVO> cmtList;
	
	//생성자에서 모든 값들이 계산되어 설정되어야 함
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		// 1~10 / 11~20 / 21~30
		// 1 2  3... 10 => 10 endpage는 변함이없음
		// 1 / 10 나머지를 올려 1로 만들어 *10
		// 2 / 10 => 0,2 ceil => 1 *10 =>10
		// 정수 / 정수 => 형변환
		this.endPage = (int) Math.ceil(pgvo.getPageNo() /(double) 10)*10;
		this.startPage = endPage -9;
		
		
		// 실제 마지막페이지
		// 전체글 수 / 한페이지에 표시되는 게시글 수 => 올림
		this.realEndPage = (int) Math.ceil(totalCount /(double) pgvo.getQty()); 
		
		
		// 현재 정말 끝페이지가 28
		if(endPage > realEndPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < this.realEndPage;
		
		
	}
	
	// 댓글용
	public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO> cmtList) {
		this(pgvo, totalCount);
		this.cmtList = cmtList;
	}
	
	
	
	
	
	
	
	
	
	

}
