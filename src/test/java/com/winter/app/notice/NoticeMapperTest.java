package com.winter.app.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeMapperTest {
	
	@Autowired
	private NoticeMapper noticeMapper;

	@Test
	void getListTest()throws Exception{
		List<NoticeVO> ar = noticeMapper.getList();
		
		for(NoticeVO noticeVO: ar) {
			System.out.println(noticeVO.toString());
		}
		
		assertNotEquals(0, ar.size());
	}

}
