package com.winter.app.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//add
	@GetMapping("add")
	public void add(MemberVO memberVO)throws Exception{
		//model.addAttribute("memberVO", new MemberVO());
	}
	
	@PostMapping("add")
	public String add(@Valid MemberVO memberVO, BindingResult bindingResult)throws Exception{
		boolean check = memberService.memberValidate(memberVO, bindingResult);
		if(check) {
			return "member/add";
		}
//		if(bindingResult.hasErrors()) {
//			return "member/add";
//		}
		
		//int result = memberService.add(memberVO);
		
		return "redirect:../";
	}
	
	
	//detail
	@GetMapping("login")
	public void login()throws Exception{}
	
	@PostMapping("login")
	public String login(MemberVO memberVO, HttpSession session)throws Exception{
		memberVO = memberService.detail(memberVO);
		
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
		}
		
		return "redirect:../";
	}
	
	
	//logout
	@GetMapping("logout")
	public String logout(HttpSession session)throws Exception{
		session.invalidate();
		return "redirect:../";
	}
	
}
