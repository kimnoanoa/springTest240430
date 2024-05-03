package com.ezen.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.www.domain.BoardDTO;
import com.ezen.www.domain.BoardVO;
import com.ezen.www.domain.FileVO;
import com.ezen.www.domain.PagingVO;
import com.ezen.www.repository.BoardDAO;
import com.ezen.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
   
   
   private final BoardDAO bdao;
   private final FileDAO fdao;

@Override
public int insert(BoardDTO bdto) {
	// TODO Auto-generated method stub
	int isOk = bdao.insert(bdto.getBvo());
	if(bdto.getFlist() == null) {
		return isOk;
	}
	if(isOk>0 && bdto.getFlist().size() >0) {
		// bno setting
		int bno = bdao.selectOneBno(); // 가장 마지막에 등록된 bno 처리
		for(FileVO fvo : bdto.getFlist()) {
			fvo.setBno(bno);
			isOk *= fdao.insertFile(fvo);
		}
	}
	return isOk;
}

@Override
public List<BoardVO> getList(PagingVO pgvo) {
	// TODO Auto-generated method stub
	return bdao.getList(pgvo);
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

@Override
public int getTotal(PagingVO pgvo) {
	// TODO Auto-generated method stub
	return bdao.getTotal(pgvo);
}

}