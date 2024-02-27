package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.PersonVo;

@Service
public class GuestbookService {

	// 필드
	@Autowired
	private GuestbookDao guestbookDao;

	// 수정폼
	public PersonVo exeModifyForm(int no) {
		System.out.println("GuestbookService.exeModifyForm()");

		PersonVo personVo = guestbookDao.personSelectOne(no);

		return personVo;

	}

	// 등록
	public int exeWrite(PersonVo personVo) {
		System.out.println("GuestbookService.exeWrite()");

		// 비지니스로직X

		int count = guestbookDao.personInsert(personVo);

		return count;
	}

	// 수정
	public int exeModify(PersonVo personVo) {
		System.out.println("GuestbookService.exeWrite()");

		int count = guestbookDao.personModify(personVo);
		return count;
	}

	// 리스트
	public List<PersonVo> exeList() {
		System.out.println("GuestbookService.exeList()");

		List<PersonVo> personList = guestbookDao.personSelect();

		return personList;
	}

	// 삭제폼
	public PersonVo exeDeleteForm(int no) {
		System.out.println("GuestbookService.exeDeleteForm()");

		PersonVo personVo = guestbookDao.personSelectOne(no);

		return personVo;

	}

	// 삭제
	public int exeDelete(int no, String password) {
		System.out.println("GuestbookService.exeDelete()");

		int count = guestbookDao.personDelete(no, password);

		return count;
	}
}
