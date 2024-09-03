package com.winter.app.qna;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class QnaControllerTest {

	private WebApplicationContext ctx;
	
	@Autowired
	private MockMvc mockMvc;
	
//	@Test
//	void test() {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
//		mockMvc.perform(get("").)
//	}
	
	
	@Test
	void addTest()throws Exception{
		
		mockMvc.perform(
				post("/qna/add")
				.param("boardWriter", "ww")
				.param("boardTitle", "tt")
				.param("boardContents", "cc")
				
				)
				.andDo(print())
		;
		
		
	}
	
	@Test
	void getListTest()throws Exception{
		
		//Map 키 중복 허용 하지 않음 a, 1 => a, 2
		//MultiValueMap 키 중복 허용 : a, 1 => a, 2 -> a {1,2}
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("page", "1");
		map.add("kind", "k1");
		map.add("search", "2");
		
		Map<String, String> map2 = new HashMap<>();
		map2.put("page", "1");
		map2.put("kind", "k1");
		map2.put("search", "2");
		
		mockMvc.perform(
					get("/qna/list")
					.params(map)
//					.params(map2)
//					.param("page", "1")
//					.param("kind", "k1")
//					.param("search", "2")
				
				)
				.andDo(print())
		;
		
	}
	
	@Test
	void getDetailTest()throws Exception{
		mockMvc.perform
				(
						get("/qna/detail")
						.param("boardNum", "110")
				)
				.andExpect(status().isOk())
				.andDo(print())
		;
					
		
	}

}
