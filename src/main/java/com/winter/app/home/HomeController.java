package com.winter.app.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.winter.app.notice.NoticeMapper;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class HomeController {
	


	@GetMapping("/")
	public String home()throws Exception{
		
		log.trace("Trace");
		log.debug("Debug");
		log.info("info");
		log.warn("warn");
		log.error("error");
		
		return "index";
	}

}
