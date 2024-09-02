package com.winter.app.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.winter.app.notice.NoticeMapper;


@Controller
public class HomeController {
	
	@Autowired
	private NoticeMapper noticeDAO;

	@GetMapping("/")
	public String home()throws Exception{
		noticeDAO.getList();
		return "index";
	}

}
