package com.ezen.test.service;

import com.ezen.test.domain.MemberVO;

public interface MemberService {

	int insert(MemberVO mvo);

	MemberVO isUser(MemberVO mvo);

	void lastLoginUpdate(MemberVO mvo);

	void modify(MemberVO mvo);

	void remove();

}
