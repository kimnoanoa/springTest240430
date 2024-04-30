package com.ezen.test.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.test.domain.BoardDTO;
import com.ezen.test.domain.BoardVO;
import com.ezen.test.domain.FileVO;
import com.ezen.test.domain.PagingVO;
import com.ezen.test.repository.BoardDAO;
import com.ezen.test.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	private final BoardDAO bdao;
	private final FileDAO fdao;

	@Override
	public int insert(BoardDTO bdto) {
		log.info(">>>> Board register service check!");
//		return bdao.insert(bvo);
		int isOk = bdao.insert(bdto.getBvo());
		
		
		// file 처리 -> bno는 아직 없음
		if(bdto.getFlist() == null) {
			return isOk;
		}else {
			//파일저장
			if(isOk > 0 && bdto.getFlist().size() >0) {
				// bno 는 아직 없음. insert 를 통해서 자동생성 => DB에서 검색해와야함
				int bno = bdao.selectBno();
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					//파일저장
					 isOk*=fdao.insertFile(fvo);
				}
			}
		}
		return isOk;
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info(">>>> Board List service check!");
		return bdao.getList(pgvo);
	}

	@Override
	public BoardDTO getDetail(int bno) {
		log.info(">>>> Board Detail service check!");
		//read_count 증가
		bdao.updateReadCount(bno);
		BoardDTO bdto = new BoardDTO();
		
		 BoardVO bvo = bdao.getDetail(bno); //기존 처리된 bvo 객체
		 bdto.setBvo(bvo);
		 bdto.setFlist(fdao.getFileList(bno)); //bno에 해당하는 모든 파일리스트 검색
		 return bdto;
	}

	@Override
	   public void update(BoardDTO bdto) {
	      log.info(">>>board update service check!");
	      //파일추가작업
	      //bvo => boardMapper /flist => fileMapper
	     int isOk = bdao.update(bdto.getBvo());
	      //파일값이없다면?
	      
	      if(bdto.getFlist() == null) {
	    	  return;
	      }
	      if(isOk > 0 && bdto.getFlist().size()>0) {
	    	  // bno setting
	    	  for(FileVO fvo : bdto.getFlist()) {
	    		  fvo.setBno(bdto.getBvo().getBno());
	    		  isOk *= fdao.insertFile(fvo);
	    	  }
	      }
	   }

	@Override
	public void delete(int bno) {
		 log.info(">>>board delete service check!");
	      bdao.delete(bno);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		log.info(">>>board total service check!");
		return bdao.getTotal(pgvo);
	}

	@Override
	public int removeFile(String uuid) {
		// TODO Auto-generated method stub
		return fdao.removeFile(uuid);
	}

	@Override
	public void cmtFileUpdate() {
		bdao.cmtCountUpdate();
		bdao.fileCountUpdate();
		
	}




}