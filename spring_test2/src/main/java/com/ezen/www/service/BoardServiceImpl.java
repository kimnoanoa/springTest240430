package com.ezen.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.BoardVO;
import com.ezen.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
   
   
   private final BoardDAO bdao;

@Override
public int insert(BoardVO bvo) {
	// TODO Auto-generated method stub
	return bdao.insert(bvo);
}

@Override
public List<BoardVO> getList() {
	// TODO Auto-generated method stub
	return bdao.getList();
}

@Override
public BoardVO getDetail(int bno) {
	// TODO Auto-generated method stub
	return bdao.getDetail(bno);
}

@Override
public int update(BoardVO bvo) {
	// TODO Auto-generated method stub
	return bdao.update(bvo);
}

@Override
public void remove(int bno) {
	// TODO Auto-generated method stub
	bdao.delete(bno);
	
}

}