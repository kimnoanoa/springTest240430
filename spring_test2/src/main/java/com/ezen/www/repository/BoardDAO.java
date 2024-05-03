package com.ezen.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.PagingVO;


@Mapper
public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	int update(BoardVO bvo);

	void delete(int bno);

	int getTotal(PagingVO pgvo);

	int selectOneBno();

	
}
