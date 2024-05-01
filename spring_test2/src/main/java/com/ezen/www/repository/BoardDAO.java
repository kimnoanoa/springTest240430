package com.ezen.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.www.domain.BoardVO;


@Mapper
public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO getDetail(int bno);

	int update(BoardVO bvo);

	void delete(int bno);

	
}
