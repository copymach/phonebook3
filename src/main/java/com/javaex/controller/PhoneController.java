package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {

//	필드
//	생성자
//	메서드 gs

//	메서드 일반

	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("PhoneController > writeForm () ");

		return "/WEB-INF/views/writeForm.jsp";
	} // writeForm 종료

//	디스패쳐 서블릿에게 personVo를 묶어오라는 코드
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController > write() ");

		// 저장하는 로직
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.contactsInput(personVo);

//		값 잘 받는지 확인
		System.out.println("스프링이 가져온 personVo " + personVo);

//		리다이렉트 - 루트 /phone 빼먹지 말자  
//		return "redirect:/phone/list";

//		상대경로로 리턴
		return "redirect:list";
	} // write 종료

	
	/*
	// 파라미터를 직접 묶어본 경우
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write2@RequestParam("name") String name,
						@RequestParam("hp") String hp,
						@RequestParam("company") String company) {
		System.out.println("PhoneController > write() ");

		// 리퀘스트에서 보낸 값 잘 받는지 확인 System.out.println(name + hp + company);

		PersonVo personVo = new PersonVo(name, hp, company);
		PhoneDao phoneDao = new PhoneDao();

		phoneDao.ContactsInput(personVo);

		return "/WEB-INF/views/...";
	}*/
						

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("PhoneController > list () 시작 ");

//		MVC model view controll 패턴 3가지를 안섞이게 하는 방향

		// Dao에서 리스트를 가져온다
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getList();
		System.out.println("============================");
		System.out.println(personList.toString());

//		컨트롤러가 DS 디스패처 서블릿에게 데이터를 보낸다 (Model - 리스트)
		model.addAttribute("personList", personList);

		// jsp 정보를 리턴한다 (view - 뷰)
		return "/WEB-INF/views/list.jsp";

	} // list 종료

	@RequestMapping(value = "/updateForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateForm(Model model, @RequestParam("id") int id) {
		System.out.println("PhoneController > updateForm () ");
		
//		RequestParam 으로 id를 가져와서
		
// 		다오를 부르고
		PhoneDao phoneDao = new PhoneDao();
		
//		getPerson에서 id로 찾고 Vo에 넣자
		PersonVo personVo = phoneDao.getPerson(id);
		System.out.println("index를 확인 "+id);
		
//		컨트롤러가 모델로 디스패처 서블릿에 데이터를 보낸다
		model.addAttribute("personVo", personVo);
		
//		return "redirect:/phone/updateForm";
		return "/WEB-INF/views/updateForm.jsp";
	} // updateForm 종료

//	디스패쳐 서블릿에게 personVo를 묶어오라는 코드
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController > update() ");
		
// 		저장하는 로직
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.contactsUpdate(personVo);

//		값 잘 받는지 확인
		System.out.println("스프링이 가져온 personVo " + personVo);

//		리다이렉트 - 루트 /phone 빼먹지 말자  
//		return "redirect:/phone/list";

//		상대경로로 리턴
		return "redirect:list";
	} // update 종료

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete (@RequestParam("id") int id) {
		System.out.println("PhoneController > update() ");
		
// 		저장하는 로직
		PhoneDao phoneDao = new PhoneDao();
		phoneDao.contactsRemove(id);

//		값 잘 받는지 확인
		System.out.println("스프링이 가져온 id " + id);

//		상대경로로 리턴
		return "redirect:list";
	} // update 종료

	
	
	
}  // The end of this PhoneController