package com.ezen.test.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.test.domain.MemberVO;
import com.ezen.test.repository.MemberDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class MemberServiceImpl implements MemberService {

	private final MemberDAO mdao;
	final BCryptPasswordEncoder passwordEncoder;
	final HttpServletRequest request;

	@Override
	public int insert(MemberVO mvo) {
		// 아이디가 중복되는 경우 회원가입 실패
		//아이디만 주고 DB에서 MVO 객체를 리턴 => 일치하는 객체가 있다면 가입실패 / 없으면 가능.
		MemberVO tempMvo = mdao.getUser(mvo.getId());
		
		if(tempMvo != null) {
			//기존 아이디가 있는 경우
			return 0;
		}
		
		//아이디가 중복되지 않는다면... 회원가입 진행.
		//passowrd가 null이거나 값이 없다면.... 가입 불가
		if(mvo.getId() == null || mvo.getId().length() == 0) {
			return 0;
		}
		
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
			return 0;
		}
		
		//회원가입 진행
		//passowrd 암호화 하여 가입
		//encode() : 암호화를 진행하는 메서드 / matches(입력된 비번, 암호화된 비번) => true / false로 맞는지 안맞는지 리턴
		mvo.setPw(passwordEncoder.encode(mvo.getPw()));
		
		//회원가입
		
		int isOk = mdao.insert(mvo);
		
		return isOk;
		
	}

	@Override
	public MemberVO isUser(MemberVO mvo) {
		
		//로그인 유저 확인
		
		MemberVO tempMvo = mdao.getUser(mvo.getId()); //회원가입 했을 때 썼던 메서드 활용
		
		if(tempMvo == null) {
			return null;
		}
		
		//matches(원래비번, 암호화된비번) 비교
		if(passwordEncoder.matches(mvo.getPw(), tempMvo.getPw())) {
			return tempMvo;
		}
		
		return null; 
	}

	@Override
	public void lastLoginUpdate(MemberVO mvo) {
		
		mdao.lastLoginUpdate(mvo);
		
	}

	@Override
	public void modify(MemberVO mvo) {
		
		//pw 여부에 따라 변경사항을 나누어서 처리
		//ow가 없다면 기존값 설정 / 있다면 암호화 처리하여 수정
		
		if(mvo.getPw() == null || mvo.getPw().length() == 0) {
		MemberVO sesMvo =  (MemberVO)request.getSession().getAttribute("ses");
		mvo.setPw(sesMvo.getPw());		
		}else {
			
			String setPw = passwordEncoder.encode(mvo.getPw());
			mvo.setPw(setPw);
			
		}
		log.info(">>>>pw 수정 후 mvo >>>> {}",mvo);
		
		mdao.update(mvo);
	}

	@Override
	public void remove() {
		
		//세션에 삭제할 아이디 추출
		String removeId = ((MemberVO) request.getSession().getAttribute("ses")).getId();
		
		//아이디가 null이아니거나 아이디의 길이가 있을 경우
		if(removeId != null || removeId.length()>0) {
			mdao.remove(removeId);
		}
		
	}
	
	

}
