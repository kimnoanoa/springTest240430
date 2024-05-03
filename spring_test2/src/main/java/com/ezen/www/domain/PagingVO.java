package com.ezen.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PagingVO {
	
	private int pageNo;
	private int qty;
	private String type;
	private String keyword;
	

	public PagingVO() {
		this.pageNo =1;
		this.qty = 10;
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	// DB에서 사용될 시작번지구하기
	// select *from board limit 번지,개수 => 0부터 시작
	// 1page limit 0, 10 => 2page limit 10,10
	
	public int getPageStart() {
		return (this.pageNo -1)*this.qty;
	}
	
	// type 의 복합타입을 배열로 생성
	// 복합타입의 키워드일경우 각자 검색하게 하기위해 배열로 생성
	
	public String[] getTypeToArray() {
		return this.type == null? new String[] {} : this.type.split("");
	}
	
	
	
	
}
