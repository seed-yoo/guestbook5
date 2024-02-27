package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.service.GuestbookService;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/gbc")
public class GuestbookController {

	// 필드
	@Autowired
	private GuestbookService guestbookService;

	// 수정폼
	@RequestMapping(value = "/modifyform", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam(value = "no") int no, Model model) {
		System.out.println("/phone/modifyForm.jsp");

		PersonVo personVo = guestbookService.exeModifyForm(no);

		// PhonebookDao phonebookDao = new PhonebookDao();
		// PersonVo personVo = phonebookDao.personSelectOne(no);
		// System.out.println(personVo);

		model.addAttribute("pVo", personVo);
		return "/modifyForm";
	}

	// 등록폼
	@RequestMapping(value = "/addlist", method = { RequestMethod.GET, RequestMethod.POST })
	public String addListForm(Model model) {
		System.out.println("/gbc/addlistForm.jsp");

		List<PersonVo> personList = guestbookService.exeList();

		model.addAttribute("pList", personList);

		return "/addList";
	}

	// 등록2
	@RequestMapping(value = "/write2", method = { RequestMethod.GET, RequestMethod.POST })
	public String write2(@ModelAttribute PersonVo personVo) {
		System.out.println("guestbookController.write2()");

		System.out.println(personVo.toString());

		// 서비스를 메모리에 올리고
		// 서비스의 메소드 사용
		guestbookService.exeWrite(personVo);

		// 리스트로 리다이렉트
		return "redirect:/gbc/addlist";

	}

	// 등록
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@RequestParam(value = "name") String name, @RequestParam(value = "hp") String hp,
			@RequestParam(value = "company") String company) {
		System.out.println("guestbookController.write()");

		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);

		// vo 묶는다
		PersonVo personVo = new PersonVo(name, hp, company);

		// dao를 메모리에 올린다
		GuestbookDao guestbookDao = new GuestbookDao();
		// dao.personInsert(vo) 저장한다
		guestbookDao.personInsert(personVo);

		// 리스트로 리다이렉트
		return "redirect:/phone/list";
	}

	// 삭제폼
	@RequestMapping(value = "/deleteform", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(@RequestParam(value = "no") int no, Model model) {
		System.out.println("/gbc/deleteForm.jsp");
		PersonVo personVo = guestbookService.exeDeleteForm(no);

		model.addAttribute("pVo", personVo);
		return "/deleteForm";

	}

	// 삭제
	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@RequestParam(value = "no") int no, @RequestParam(value = "password") String password) {

		System.out.println("guestbookController.delete()");

		// System.out.println(personId);

		guestbookService.exeDelete(no, password);

		// 리스트로 리다이렉트
		return "redirect:/gbc/addlist";

	}

}
