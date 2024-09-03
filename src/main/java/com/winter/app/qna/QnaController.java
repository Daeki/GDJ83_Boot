package com.winter.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;



@Controller
@RequestMapping("/qna/*")
@Slf4j
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@GetMapping("list")
	public String getList(Pager pager, Model model)throws Exception{
		List<QnaVO> ar = qnaService.getList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		log.info("Pager : {} : {}", pager, pager.getKind());
		return "redirect:../";
	}

}











